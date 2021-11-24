<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="内容类目ID" prop="categoryId">
      <el-input v-model="dataForm.categoryId" placeholder="内容类目ID"></el-input>
    </el-form-item>
    <el-form-item label="内容标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="内容标题"></el-input>
    </el-form-item>
    <el-form-item label="链接" prop="url">
      <el-input v-model="dataForm.url" placeholder="链接"></el-input>
    </el-form-item>
    <el-form-item label="图片绝对路径" prop="pic">
      <el-input v-model="dataForm.pic" placeholder="图片绝对路径"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
    </el-form-item>
    <el-form-item label="排序" prop="sortOrder">
      <el-input v-model="dataForm.sortOrder" placeholder="排序"></el-input>
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
          categoryId: '',
          title: '',
          url: '',
          pic: '',
          status: '',
          sortOrder: ''
        },
        dataRule: {
          categoryId: [
            { required: true, message: '内容类目ID不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '内容标题不能为空', trigger: 'blur' }
          ],
          url: [
            { required: true, message: '链接不能为空', trigger: 'blur' }
          ],
          pic: [
            { required: true, message: '图片绝对路径不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ],
          sortOrder: [
            { required: true, message: '排序不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/manager/content/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.categoryId = data.content.categoryId
                this.dataForm.title = data.content.title
                this.dataForm.url = data.content.url
                this.dataForm.pic = data.content.pic
                this.dataForm.status = data.content.status
                this.dataForm.sortOrder = data.content.sortOrder
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
              url: this.$http.adornUrl(`/manager/content/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'categoryId': this.dataForm.categoryId,
                'title': this.dataForm.title,
                'url': this.dataForm.url,
                'pic': this.dataForm.pic,
                'status': this.dataForm.status,
                'sortOrder': this.dataForm.sortOrder
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
