<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="区域ID" prop="areaid">
      <el-input v-model="dataForm.areaid" placeholder="区域ID"></el-input>
    </el-form-item>
    <el-form-item label="区域名称" prop="area">
      <el-input v-model="dataForm.area" placeholder="区域名称"></el-input>
    </el-form-item>
    <el-form-item label="城市ID" prop="cityid">
      <el-input v-model="dataForm.cityid" placeholder="城市ID"></el-input>
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
          areaid: '',
          area: '',
          cityid: ''
        },
        dataRule: {
          areaid: [
            { required: true, message: '区域ID不能为空', trigger: 'blur' }
          ],
          area: [
            { required: true, message: '区域名称不能为空', trigger: 'blur' }
          ],
          cityid: [
            { required: true, message: '城市ID不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/manager/areas/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.areaid = data.areas.areaid
                this.dataForm.area = data.areas.area
                this.dataForm.cityid = data.areas.cityid
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
              url: this.$http.adornUrl(`/manager/areas/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'areaid': this.dataForm.areaid,
                'area': this.dataForm.area,
                'cityid': this.dataForm.cityid
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
