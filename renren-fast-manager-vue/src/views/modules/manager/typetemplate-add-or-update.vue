<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    {{dataForm}}
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
    <el-form-item label="模板名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="模板名称"></el-input>
    </el-form-item>
      <el-form-item label="关联规格" prop="specIds">
      <!--      <el-input v-model="dataForm.specIds" placeholder="关联规格"></el-input>-->
      <el-select v-model="dataForm.specIds" value-key="id" multiple placeholder="请选择">
        <el-option
        v-for="item in specs"
        :key="item.id"
        :label="item.text"
        :value="item"
        >
        </el-option>
      </el-select>
    </el-form-item>

      <el-form-item label="关联品牌" prop="brandIds">
        <el-select v-model="dataForm.brandIds" value-key="id" multiple placeholder="请选择">
          <el-option
            v-for="item in brand"
            :key="item.id"
            :label="item.text"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="自定义属性" prop="customAttributeItems">
        <el-button type="success" size="small" @click="dataForm.customAttributeItems.push({})">新增拓展属性</el-button>
        <el-table
          :data="dataForm.customAttributeItems"
          style="width: 100%;">
          <el-table-column
            prop="id"
            header-align="left"
            align="left"
            label="属性名称">
            <template slot-scope="scope">
              <el-input v-model="scope.row.text"></el-input>
            </template>
          </el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            width="150"
            label="操作">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="dataForm.customAttributeItems.splice(scope.index,1)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          specIds: '',
          brandIds: '',
          customAttributeItems: []
        },
        specs:[],     //查询所有的规格
        brand:[],     //
        dataRule: {
          name: [
            { required: true, message: '模板名称不能为空', trigger: 'blur' }
          ],
          specIds: [
            { required: true, message: '关联规格不能为空', trigger: 'blur' }
          ],
          brandIds: [
            { required: true, message: '关联品牌不能为空', trigger: 'blur' }
          ],
          customAttributeItems: [
            { required: true, message: '自定义属性不能为空', trigger: 'blur' }
          ]
        }
      }
    },

    created() {
      //1. 查询所有的规格
      this.findSpecs()
      //2. 查询所有品牌
      this.findBrands()
    },

    methods: {
      // 1. 查询所有的规格
      findSpecs(){
        this.$http({
          url:this.$http.adornUrl(`/manager/specification/findAll`),
          method:'get',
          params: this.$http.adornParams()
        }).then(({data})=>{
          // console.log(data.list)
            this.specs=data.list.map(m=>{
              return{'id':m.id,'text':m.specName};
            })
        });
      },
      //2. 查询所有品牌
      findBrands(){
        this.$http({
          url:this.$http.adornUrl(`/manager/brand/findAll`),
          method:'get',
          params: this.$http.adornParams()
        }).then(({data})=>{
          // console.log(data.list)
          this.brand=data.list.map(m=>{
            return{'id':m.id,'text':m.name};
          })
        });
      },

      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/manager/typetemplate/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                let specIds=JSON.parse(data.typeTemplate.specIds).map(m=>{
                  return {"id":m.id+"","text":m.text}
                });
                let brandIds = JSON.parse(data.typeTemplate.brandIds).map(m=>{
                  return {"id":m.id+"","text":m.text}
                });
                this.dataForm.specIds=specIds
                this.dataForm.brandIds = brandIds
                this.dataForm.customAttributeItems = JSON.parse(data.typeTemplate.customAttributeItems)
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/manager/typetemplate/${!this.dataForm.id ? 'save' : 'update'}`),
              method: `${this.dataForm.id ? 'put' : 'post'}`,
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'specIds': JSON.stringify(this.dataForm.specIds),
                'brandIds': JSON.stringify(this.dataForm.brandIds),
                'customAttributeItems': JSON.stringify(this.dataForm.customAttributeItems)
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
