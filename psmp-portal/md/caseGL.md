#案例管理   案例维护  接口文档 
## 获取  案例的详细
> 请求方式：GET  
> 请求地址：[/api/psmp-portal/portal/cases/{case的id}] 
返回 案例 成功数据格式
```
{
    "code": 0,
    "data": {
          "caid": "dsds",
          "zflid": 2,
          "num": 13,
          "title": "dsregr",
          "plurl": "grferfer",
          "tpgs": "dsrers",
          "tpdx": "dsered",
          "source": "dfdferfsfs",
          "hp": "d",
          "state": "f",
          "time": "2018-12-28T11:05:50.000+0000",
          "content": "dsdserss"
    }
}
```
## 获取  案例列表信息
> 请求方式：GET  
> 请求参数："zflid":"分类编号","time": "创建时间","title": "主题","currentPage":"页码","pageSize":"每页数据量"   
> 请求地址：[/api/psmp-portal/portal/cases]  
返回  案例  成功数据格式 
```
{
    "code": 0,
        "data": {
            [
               "0":{
                      casid :"dsda"
                      title :"dsds7676"
               }
               "1":{
                    "caid": "dsds",
                    "zflid": 2,
                    "num": 13,
                    "title": "dsregr",
                    "plurl": "grferfer",
                    "tpgs": "dsrers",
                    "tpdx": "dsered",
                    "source": "dfdferfsfs",
                    "hp": "d",
                    "state": "f",
                    "time": "2018-12-28T11:05:50.000+0000",
                    "content": "dsdserss"
                   }
           ]
        }
}
```
## 增加   案例信息  操作   
> 请求方式：POST   
> 请求参数：{"title": "主题",”zflid“:"子分类id","plurl": "图片路径","num": "排序","state":"状态","content":"内容“,"source":"来源","hp":"首页显示"}
> 请求地址：[/api/psmp-portal/portal/cases]
返回  案例  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 修改   案例信息  操作   
> 请求方式：PUT   
> 请求参数：{”caid“:"案例ID",”zflid“:"子分类id","title": "主题","plurl": "图片路径","num": "排序","state":"状态",content":"内容“,"source":"来源","hp":"首页显示"}
> 请求地址：[/api/psmp-portal/portal/cases]
返回  案例  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 删除   案例信息  操作   
> 请求方式：DELETE   
> 请求地址：[/api/psmp-portal/portal/cases/{case的id}]
返回  案例  成功数据格式
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
> 请求地址：[/api/psmp-portal/portal/caseUploads]  
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