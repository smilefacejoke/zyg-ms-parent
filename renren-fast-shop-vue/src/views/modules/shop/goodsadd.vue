<template>
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


        <el-form-item>
          <el-button type="primary" @click="save">保存</el-button>
          <el-button>返回列表</el-button>
        </el-form-item>
      </el-form>


    </el-tab-pane>
    <el-tab-pane label="商品图片">

    </el-tab-pane>
    <el-tab-pane label="拓展属性">

    </el-tab-pane>
    <el-tab-pane label="规格">

    </el-tab-pane>
  </el-tabs>
</template>

<script>
  export default {
    data () {
      return {
        goodsForm:{
          goods:{
            typeTemplateId:'',
          },
          goodsDesc:{},
          items:[]
        },
        categorys1:[],  //一级分类
        categorys2:[],  //二级分类\
        categorys3:[],  //三级分类
        brandList:[], //代表某个模板品牌的列表


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
        this.$http({
          url: this.$http.adornUrl(`/shop/typetemplate/info/${typeId}`),
          method:'get',
        }).then(({data})=>{
          if (data.code==0){
            this.brandList=JSON.parse(data.typeTemplate.brandIds);
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

      save(){

      },
    }
  }
</script>
