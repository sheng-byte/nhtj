import request from '@/utils/request'

// 登录方法
export function treeList(pno,psize,orderBy,ascOrDesc){
	return request({
		url:`/tree/tree/${pno}/${psize}/${orderBy}/${ascOrDesc}`
	})
}
export function addtree(tree){
	return request({
		url:`/tree/tree/addtree`,
		method:"post",
		data:tree
	})
}
export function updatetree(tree){
	return request({
		url:`/tree/tree`,
		method:"put",
		data:tree
	})
}

export function getone(tid){
	return request({
		url:`/tree/tree/${tid}`,
		method:"get",
		
	})
}

export function deltree(tid){
	return request({
		url:`/tree/tree/${tid}`,
		method:"delete",
		
	})
}