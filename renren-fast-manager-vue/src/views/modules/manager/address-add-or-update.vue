<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="省" prop="provinceId">
      <el-input v-model="dataForm.provinceId" placeholder="省"></el-input>
    </el-form-item>
    <el-form-item label="市" prop="cityId">
      <el-input v-model="dataForm.cityId" placeholder="市"></el-input>
    </el-form-item>
    <el-form-item label="县/区" prop="townId">
      <el-input v-model="dataForm.townId" placeholder="县/区"></el-input>
    </el-form-item>
    <el-form-item label="手机" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="手机"></el-input>
    </el-form-item>
    <el-form-item label="详细地址" prop="address">
      <el-input v-model="dataForm.address" placeholder="详细地址"></el-input>
    </el-form-item>
    <el-form-item label="联系人" prop="contact">
      <el-input v-model="dataForm.contact" placeholder="联系人"></el-input>
    </el-form-item>
    <el-form-item label="是否是默认 1默认 0否" prop="isDefault">
      <el-input v-model="dataForm.isDefault" placeholder="是否是默认 1默认 0否"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="notes">
      <el-input v-model="dataForm.notes" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="创建日期" prop="createDate">
      <el-input v-model="dataForm.createDate" placeholder="创建日期"></el-input>
    </el-form-item>
    <el-form-item label="别名" prop="alias">
      <el-input v-model="dataForm.alias" placeholder="别名"></el-input>
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
          userId: '',
          provinceId: '',
          cityId: '',
          townId: '',
          mobile: '',
          address: '',
          contact: '',
          isDefault: '',
          notes: '',
          createDate: '',
          alias: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          provinceId: [
            { required: true, message: '省不能为空', trigger: 'blur' }
          ],
          cityId: [
            { required: true, message: '市不能为空', trigger: 'blur' }
          ],
          townId: [
            { required: true, message: '县/区不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '手机不能为空', trigger: 'blur' }
          ],
          address: [
            { required: true, message: '详细地址不能为空', trigger: 'blur' }
          ],
          contact: [
            { required: true, message: '联系人不能为空', trigger: 'blur' }
          ],
          isDefault: [
            { required: true, message: '是否是默认 1默认 0否不能为空', trigger: 'blur' }
          ],
          notes: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          createDate: [
            { required: true, message: '创建日期不能为空', trigger: 'blur' }
          ],
          alias: [
            { required: true, message: '别名不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/address/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.address.userId
                this.dataForm.provinceId = data.address.provinceId
                this.dataForm.cityId = data.address.cityId
                this.dataForm.townId = data.address.townId
                this.dataForm.mobile = data.address.mobile
                this.dataForm.address = data.address.address
                this.dataForm.contact = data.address.contact
                this.dataForm.isDefault = data.address.isDefault
                this.dataForm.notes = data.address.notes
                this.dataForm.createDate = data.address.createDate
                this.dataForm.alias = data.address.alias
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
              url: this.$http.adornUrl(`/manager/address/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'provinceId': this.dataForm.provinceId,
                'cityId': this.dataForm.cityId,
                'townId': this.dataForm.townId,
                'mobile': this.dataForm.mobile,
                'address': this.dataForm.address,
                'contact': this.dataForm.contact,
                'isDefault': this.dataForm.isDefault,
                'notes': this.dataForm.notes,
                'createDate': this.dataForm.createDate,
                'alias': this.dataForm.alias
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
