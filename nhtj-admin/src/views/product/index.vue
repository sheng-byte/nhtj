<template>
	 <!-- <el-table :data="datas" border style="width: 100%">
	    <el-table-column prop="tname" label="Date" width="180" />
	    <el-table-column prop="tcolor" label="Name" width="180" />
	  </el-table> -->
	  <!-- <textarea name="" id="" cols="30" rows="10"></textarea> -->
	<el-button type="primary" @click="addtrees()" style="float:right;">添加</el-button>
	<el-table :data="datas" style="width: 100%" @sort-change="sortList">
	    <el-table-column label="面包名字" prop="tname" sortable="custom"/>
	    <el-table-column label="面包价格" prop="tfl" sortable="custom"/>
		<el-table-column label="面包克数" prop="tcolor" sortable="custom"/>
		<el-table-column label="保质期" prop="ttime"/>
		<el-table-column label="图片"  >
		  <template #default="scope">
		  	<img style="width: 100px; height: 100px;" :src="$server_pic+scope.row.image" />
		  </template>
		</el-table-column>
	    <el-table-column label="操作" align="right">
	      <template #default="scope">
	        <el-button size="small" @click="handleEdit(scope.row.tid)">
	          Edit
	        </el-button>
			<el-popconfirm title="真的要删除吗" @confirm="handleDelete(scope.row.tid)">
				<template #reference>
			<el-button size="small" type="danger" >
				Delete
			</el-button>
			</template>
			</el-popconfirm>
	        
	      </template>
	    </el-table-column>
	  </el-table>
	  <el-pagination
	        v-model:current-page="pno"
	        v-model:page-size="psize"
	        :page-sizes="[5, 10, 15, 20]"
	        :background="true"
	        layout="total, sizes, prev, pager, next"
	        :total="total"
	        @size-change="handleSizeChange"
	        @current-change="handleCurrentChange"
	      />
		   <el-dialog v-model="dialogFormVisible" title="面包管理" width="500">
		      <el-form :model="tree">
		        <el-form-item label="面包名称" :label-width="formLabelWidth">
		          <el-input v-model="tree.tname" autocomplete="off" />
		        </el-form-item>
				<el-form-item label="面包价格" :label-width="formLabelWidth">
				  <el-input v-model="tree.tcolor" autocomplete="off" />
				</el-form-item>
				<el-form-item label="保质期" :label-width="formLabelWidth">
				  <el-input v-model="tree.ttime" autocomplete="off" />
				</el-form-item>
				<el-form-item label="图片" :label-width="formLabelWidth">
				  <el-upload
				      class="avatar-uploader"
				  	:headers="headers"
				      :action="$server_upload"
				  	:show-file-list="false"
				      :on-success="handAvatarSuccess"  
				    >
				    <img v-if="imageUrl" :src="imageUrl" class="avatar"/>
				     <el-icon v-else class="avatar-uploader-icon"><Plus/></el-icon>
				    </el-upload>
				</el-form-item>
				<el-form-item label="推荐选择">
								    <el-checkbox v-model="tree.spring" label="巧克力酱"  size="large"/>
									<el-checkbox v-model="tree.summer" label="蜂蜜"  size="large"/>
									<el-checkbox v-model="tree.autumn" label="芥末"  size="large"/>
									<el-checkbox v-model="tree.winter" label="奶油"  size="large"/>	  
				</el-form-item>
				<el-form-item label="面包介绍">
								  <Editor :height="300" v-model="tree.tfl" :key="tree.tid"></Editor>
				</el-form-item>
		        
		      </el-form>
		      <template #footer>
		        <div class="dialog-footer">
		          <el-button @click="dialogFormVisible = false">Cancel</el-button>
		          <el-button type="primary" @click="save()">
		            Confirm
		          </el-button>
		        </div>
		      </template>
		    </el-dialog>
</template>

<script setup>
	
	
	import { ref } from 'vue';
	import {treeList,updatetree,getone,addtree,deltree} from '../../api/treeApi.js'
	import { ElMessage } from 'element-plus';
	const {proxy} = getCurrentInstance()
	// import { tr } from 'element-plus/es/locale'
	const datas=ref([])
	const pno = ref(1)
	const psize = ref(5)
	const orderby = ref('tid')
	const ascOrDesc = ref('desc')
	const total = ref(0)
	const dialogFormVisible = ref(false)
	const tree=ref({
		tname:"",
		tfl:"",
		spring:"",
		summer:"",
		auttumn:"",
		winter:"",
		image:"",
		tcolor:""
	})
	async function handleDelete(tid){
		const res = await deltree(tid)
		getData()
	}
	tree.value.tid = ref(0)
	async function handleEdit(tid){
		console.log(tid);
		const res = await getone(tid)
		tree.value = res.data
		dialogFormVisible.value = true
		console.log(tree.value.tid);
		imageUrl.value=proxy.$server_pic+tree.value.image
		tree.value.spring = tree.value.spring==1?true:false;
		tree.value.summer = tree.value.summer==1?true:false;
		tree.value.autumn = tree.value.autumn==1?true:false;
		tree.value.winter = tree.value.winter==1?true:false;
		
		
	}
	async function save(){
		let msg =""
		if(tree.tname==""){
			msg = msg+"面包名称不能为空"
		}
		if(msg!=""){
			ElMessage(msg)
		}else{
			tree.value.spring = tree.value.spring?1:0
			tree.value.summer = tree.value.summer?1:0
			tree.value.autumn = tree.value.autumn?1:0
			tree.value.winter = tree.value.winter?1:0
			if(tree.value.tid>0){
				const res = await updatetree(tree.value)
			}else{
				const res = await addtree(tree.value)
				
			}
			
			ElMessage("提交成功")
			dialogFormVisible.value = false
			getData()
			tree.value = {
			tname:"",
			tfl:"",
			spring:"",
			summer:"",
			auttumn:"",
			winter:"",
			image:"",
			tcolor:""
			}
			imageUrl.value=""
		}
	}
	const imageUrl = ref()
	function handAvatarSuccess(response,uploadFile){
		imageUrl.value = URL.createObjectURL(uploadFile.raw)
		console.log(response.data)
		tree.value.image = response.data
		
	}
	function sortList(e){
		console.log(e);
		ascOrDesc.value = e.order=="descending"?"desc":"asc"
		orderby.value = e.prop
		getData()

	}
	function handleSizeChange(e){
		psize.value = e
		getData()
	}
	function handleCurrentChange(e){
		pno.value =e
		getData()
	}
	function addtrees(){
		dialogFormVisible.value = true
	}
	async function getData(){
		const res = await treeList(pno.value,psize.value,orderby.value,ascOrDesc.value)
		console.log(res);
		datas.value = res.data.records
		total.value = res.data.total
		console.log(datas.value);
	}
	getData()
	
</script>


<style>
	.avatar-uploader .avatar {
	  width: 178px;
	  height: 178px;
	  display: block;
	}
	.avatar-uploader .el-upload {
	  border: 1px dashed var(--el-border-color);
	  border-radius: 6px;
	  cursor: pointer;
	  position: relative;
	  overflow: hidden;
	  transition: var(--el-transition-duration-fast);
	}
	
	.avatar-uploader .el-upload:hover {
	  border-color: var(--el-color-primary);
	}
	
	.el-icon.avatar-uploader-icon {
	  font-size: 28px;
	  color: #8c939d;
	  width: 178px;
	  height: 178px;
	  text-align: center;
	}
	
</style>