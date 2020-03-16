#资料管理   资料维护  接口文档 
## 获取  资料的详细
> 请求方式：GET  
> 请求地址：[/api/psmp-portal/portal/materials/{资料的id}] 
返回 资料 成功数据格式
```
{
    "code": 0,
    "data": {
              "id": "qqq",
              "zflid": 12,
              "num": 1,
              "title": "dsds",
              "plurl": "ds",
              "tpgs": "dssd",
              "tpdx": "dsds",
              "state": "dq",
              "fjurl": "fdf",
              "source": "fdfd",
              "line": "dsd",
              "time": "2018-12-28T11:39:33.000+0000",
              "content": "ds"
    }
}
```
## 获取  资料列表信息
> 请求方式：GET  
> 请求参数："zflid":"分类编号","time": "创建时间","title": "主题","currentPage":"页码","pageSize":"每页数据量"   
> 请求地址：[/api/psmp-portal/portal/materials]  
返回  资料  成功数据格式 
```
{
    "code": 0,
        "data": {
            [
               "0":{
                     "id":"fwew",
                     "title":"frfref"
               }
               "1":{
                        "id": "qqq",
                        "zflid": 12,
                        "num": 1,
                        "title": "dsds",
                        "plurl": "ds",
                        "tpgs": "dssd",
                        "tpdx": "dsds",
                        "state": "dq",
                        "fjurl": "fdf",
                        "source": "fdfd",
                        "line": "dsd",
                        "time": "2018-12-28T11:39:33.000+0000",
                        "content": "ds"
                   }
           ]
        }
}
```
## 增加   资料信息  操作   
> 请求方式：POST   
> 请求参数：{"title": "主题",”zflid“:"子分类id","plurl": "图片路径","num": "排序","state":"状态","content":"内容“,"source":"来源","fjurl":"附件","line":"上/下线"}
> 请求地址：[/api/psmp-portal/portal/materials]
返回  资料  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 修改   资料信息  操作   
> 请求方式：PUT   
> 请求参数：{”id“:"资料ID",”zflid“:"子分类id","title": "主题","plurl": "图片路径","num": "排序","state":"状态",content":"内容“,"source":"来源","fjurl":"附件","line":"上/下线"}
> 请求地址：[/api/psmp-portal/portal/materials]
返回  资料  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 删除   资料信息  操作   
> 请求方式：DELETE   
> 请求地址：[/api/psmp-portal/portal/materials/{资料的id}]
返回  资料  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 上传
> 请求方式：POST  
> 请求参数：file:name标签名
> 请求地址：[/api/psmp-portal/portal/materialUploads]  
返回  上传  成功数据格式 
```
{
    "code": 0,
        "data": {
            fileName :"/p1546585249681.doc"
            fileSize :"440832"
            fileType :"doc"
        }
}
``