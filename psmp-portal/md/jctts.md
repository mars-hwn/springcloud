#jctts管理  jctts维护  接口文档 
## 获取  jctts详细信息
> 请求方式：GET  
> 请求地址：[/api/psmp-portal/portal/jcttss/{id}]  
返回  jctts  成功数据格式 
```
{
    "code": 200,
      "data": {
        "id": "d7sds",
        "zflid": 55,
        "num": "pp",
        "company": "fdfs",
        "tel": 11,
        "intro": "dsdss"
      }

}
```
## 获取  jctts列表信息
> 请求方式：GET  
> 请求参数："zflid":"分类编号","currentPage":"页码","pageSize":"每页数据量"   
> 请求地址：[/api/psmp-portal/portal/jcttss]  
返回  jctts  成功数据格式 
```
{
    "code": 0,
        "data": {
            [
                  {  "id": "d71sd1s",
                    "zflid": 55,
                    "num": "pp",
                    "company": "fdfs",
                    "tel": 11,
                    "intro": "dsdss"
                  }, {
                    "id": "d7sd1s",
                    "zflid": 55,
                    "num": "pp",
                    "company": "fdfs",
                    "tel": 11,
                    "intro": "dsdss"
                  }, {
                    "id": "d7sds",
                    "zflid": 55,
                    "num": "pp",
                    "company": "fdfs",
                    "tel": 11,
                    "intro": "dsdss"
                  }, {
                    "id": "dsds",
                    "zflid": 55,
                    "num": "pp"
                    }
           ]
        }
}
```
## 增加   jctts信息  操作   
> 请求方式：POST   
> 请求参数：{"company": "公司",”zflid“:"子分类id","intro": "介绍主要节能业务及技术产品","num": "序号","tel":"联系电话"}
> 请求地址：[/api/psmp-portal/portal/jcttss]
返回  jctts  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 修改   jctts信息  操作   
> 请求方式：PUT   
> 请求参数：{”id“:"jcttsID",”zflid“:"子分类id","company": "公司","intro": "介绍主要节能业务及技术产品","num": "序号","tel":"联系电话"}
> 请求地址：[/api/psmp-portal/portal/jcttss]
返回  资料  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 删除   jctts信息  操作   
> 请求方式：DELETE   
> 请求地址：[/api/psmp-portal/portal/jcttss/{jctts的id}]
返回  jctts  成功数据格式
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
> 请求地址：[/api/psmp-portal/portal/jcttsUploads]  
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