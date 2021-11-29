<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="商品基本信息">
        <el-form ref="goods" :model="goodsForm" label-width="200px">
          {{goodsForm}}
          <el-form-item label="商品分类:">
            <!--          1. 查询一级分类-->
            <el-select v-model="goodsForm.goods.category1Id" placeholder="请选择一级分类">
              <el-option
                v-for="cate in categorys1"
                :label="cate.name"
                :value="cate.id"
                :key="cate.id"
              >
              </el-option>
            </el-select>
            <!--          2. 查询二级分类-->
            <el-select v-model="goodsForm.goods.category2Id" placeholder="请选择二级分类">
              <el-option
                v-for="cate in categorys2"
                :label="cate.name"
                :value="cate.id"
                :key="cate.id"
              >
              </el-option>
            </el-select>

            <!--          3. 查询三级分类-->
            <el-select v-model="goodsForm.goods.category3Id" placeholder="请选择三级分类">
              <el-option
                v-for="cate in categorys3"
                :label="cate.name"
                :value="cate.id"
                :key="cate.id"
              >
              </el-option>
            </el-select>
            模板ID：{{goodsForm.goods.typeTemplateId}}
          </el-form-item>


          <el-form-item label="商品名称:">
            <el-input v-model="goodsForm.goodsName" style="width: 90%"></el-input>
          </el-form-item>


          <el-form-item label="品牌:">
            <el-select v-model="goodsForm.goods.brandId" placeholder="请选择活动区域">
              <el-option v-for="brand in brandList"
                         :label="brand.text"
                         :value="brand.id"
                         :key="brand.id"
              ></el-option>
            </el-select>
          </el-form-item>


          <el-form-item label="副标题:">
            <el-input v-model="goodsForm.goods.caption" ></el-input>
          </el-form-item>

          <el-form-item label="价格:">
            <el-input v-model="goodsForm.goods.price" ></el-input>
          </el-form-item>

          <el-form-item label="商品介绍:">
            <el-input type="textarea" v-model="goodsForm.goodsDesc.introduction"></el-input>
          </el-form-item>

          <el-form-item label="包装列表:">
            <el-input type="textarea" v-model="goodsForm.goodsDesc.packageList"></el-input>
          </el-form-item>

          <el-form-item label="售后服务:">
            <el-input type="textarea" v-model="goodsForm.goodsDesc.saleService"></el-input>
          </el-form-item>

        </el-form>
      </el-tab-pane>

      <el-tab-pane label="商品图片">
        <el-button type="success" @click="dialogFormVisible=true, imageEntity={},fileList=[]" plain size="small" >新建</el-button>
        <el-table
          :data="goodsForm.goodsDesc.itemImages"
          style="width: 100%;">
          <el-table-column
            prop="color"
            header-align="center"
            align="center"
            label="颜色">
          </el-table-column>

          <el-table-column
            header-align="center"
            align="center"
            label="图片">
            <template slot-scope="scope">
              <img :src="scope.row.url"  width="100px" height="100px">
            </template>
          </el-table-column>

          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="goodsForm.goodsDesc.itemImages.splice(scope.row.index,1)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>


      <el-tab-pane label="拓展属性">
        {{goodsForm.goodsDesc.customAttributeItems}}
        <el-table
          :data="goodsForm.goodsDesc.customAttributeItems"
          style="width: 100%;">
          <el-table-column
            prop="text"
            width="100px"
            header-align="center"
            align="center">
          </el-table-column>
          <el-table-column
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <el-input v-model="scope.row.value"></el-input>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="规格">
        {{optionList}}
        <el-table
          :data="specGroup"
          style="width: 100%;">
          <el-table-column
            width="150px"
            header-align="center"
            align="center">
            <template slot-scope="scope">
              <span>{{scope.row.text}}</span>
            </template>
          </el-table-column>
          <el-table-column
            header-align="center"
            align="left">
            <template slot-scope="scope">
              <el-checkbox-group size="small"  v-model="optionList[scope.$index]">
                <el-checkbox :label="option.optionName" border  v-for="option in scope.row.options" :key="option.id"></el-checkbox>
              </el-checkbox-group>
            </template>
          </el-table-column>

        </el-table>
      </el-tab-pane>
        <el-button type="primary" @click="save">保存</el-button>
        <el-button>返回列表</el-button>
    </el-tabs>


    <el-dialog title="图片" :visible.sync="dialogFormVisible">

      <el-form :model="imageEntity">
        <el-form-item label="颜色" label-width="100px">
          <el-input v-model="imageEntity.color" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="商品图片" label-width="100px">
<!--          文件上传组件-->
          <el-upload
            action=""
            class="upload-demo"
            :file-list="fileList"
            :http-request="uploadFile"
            list-type="picture">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveImage">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>

  export default {
    data () {
      return {
        goodsForm:{
          goods:{typeTemplateId:''},
          goodsDesc:{itemImages:[],customAttributeItems:[]},
          items:[],

        },
        categorys1:[],                  //一级分类
        categorys2:[],                  //二级分类
        categorys3:[],                  //三级分类
        brandList:[],                   //代表某个模板下的品牌列表
        dialogFormVisible:false,        //代表对话框的显示与隐藏
        imageEntity:{},                 //代表每次上传的文件对象
        fileList:[],                    //代表上传后的文件列表
        specGroup:[],                   //代表规格及规格列表
        optionList:[],                  //代表是否选择
      }
    },
    created() {
      //1. 查询一级分类
      this.findCategorys1();
    },

    watch:{
      // 用于监控某个变量值的变化
      category1newId(itemCatId,oldV){
        // 1. 根据一级分类的父id向后台发出查询请求，查询出此一级分类id对应的二级分类列表
        this.$http({
          url: this.$http.adornUrl(`/shop/itemCat/findByParentId/${itemCatId}`),
          method:'get',
        }).then(({data})=>{
          if (data.code==0){
            delete this.goodsForm.goods.category2Id;
            delete this.goodsForm.goods.category3Id;
            this.categorys2=data.list;
          }
        })
      },
      category2newId(itemCatId,oldV){
        // 1. 根据二级分类的父id向后台发出查询请求，查询出此二级分类id对应的三级分类列表
        this.$http({
          url: this.$http.adornUrl(`/shop/itemCat/findByParentId/${itemCatId}`),
          method:'get',
        }).then(({data})=>{
          if (data.code==0){
            delete this.goodsForm.goods.category3Id;
            this.categorys3=data.list;
          }
        })
      },
      category3newId(itemCatId,oldV){
        // 1. 根据三级分类的id向后台发出查询请求，查询出此三级分类id对应的模板id
        this.$http({
          url: this.$http.adornUrl(`/shop/itemCat/info/${itemCatId}`),
          method:'get',
        }).then(({data})=>{
          if (data.code==0){
            delete this.goodsForm.goods.brandId;
            this.goodsForm.goods.typeTemplateId=data.itemCat.typeId;
          }
        })
      },


      typeTempLateNewId(typeId,oldV){   //typeId:代表当前模板id
        // 1. 模板id发生变化就到后台查询出这个模板对应的品牌列表
        //2. 模板id发生变化，到模板对象中的扩展属性
        this.$http({
          url: this.$http.adornUrl(`/shop/typetemplate/info/${typeId}`),
          method:'get',
        }).then(({data})=>{
          if (data.code===0){
            //1. 得到模板的品牌列表
            this.brandList=JSON.parse(data.typeTemplate.brandIds);
            //2. 得到模板的扩展属性列表
            this.goodsForm.goodsDesc.customAttributeItems=JSON.parse(data.typeTemplate.customAttributeItems);
            //3. 得到模板的规格值及规格选项列表
            this.$http({
              url: this.$http.adornUrl(`shop/specification/findSpecByTypeId/${typeId}`),
              method: 'get',
            }).then(({data}) => {
              this.specGroup = data.specifications;
              console.log("data:",this.specGroup);
              this.optionList = [];
              data.specifications.forEach(sp=>{
                this.optionList.push([]);
              })
            })
          }
        })
      },



    },

    // 自动计算属性值的变化
    computed:{
      //1. 计算一级分类id是否变化
      category1newId(){
        return this.goodsForm.goods.category1Id;
      },
      //2. 计算二级分类id的值是否变化
      category2newId(){
        return this.goodsForm.goods.category2Id;
      },
      //3. 计算三级分类id的值是否变化
      category3newId(){
        return this.goodsForm.goods.category3Id;
      },
      //4. 监控模板id的变化
      typeTempLateNewId(){
        return this.goodsForm.goods.typeTemplateId;
      }
    },

    methods: {
      //1. 查询一级分类
      findCategorys1(){
        this.$http({
          url: this.$http.adornUrl('/shop/itemCat/findAll'),
          method: 'get',
        }).then(({data})=>{
          this.categorys1=data.categorys1;
        })
      },

      //2. 上传文件
      uploadFile(file){
        //1. 构造上传的表单对象
        let data=new FormData();
        //2. 添加要上传的文件数据
        data.append("file",file.file);//上传的文件对象
        //3. 清空上传的列表
        this.fileList=[];
        //4. 开始上传
        this.$http({
          url:this.$http.adornUrl('/shop/upload'),
          method:'post',
          data : data,
          header:{"Context-Type":"multipart/form-data"}
        }).then(({data})=>{
          if(data.code===0){
            //如果上传成功，就向文件列表添加文件
            this.fileList.push({name:file.raw,url:data.url});
            this.imageEntity.url=data.url;
          }
        })

      },

      save(){

      },

      //保存图片
      saveImage(){
        this.goodsForm.goodsDesc.itemImages.push(this.imageEntity);
        this.dialogFormVisible=false;

      }
    }
  }
</script>
