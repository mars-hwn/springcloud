#menu管理  menu维护  接口文档 
## 获取  menu信息
> 请求方式：GET  
> 请求地址：[/api/psmp-portal/portal/menus/{value}]  
返回  menu  成功数据格式 
```
{
    {
      "code": 200,
      "data": [{
        "value": 100101,
        "label": "新闻中心 ",
        "children": [{
          "value": 10010101,
          "label": "新闻动态",
          "children": []
        }, {
          "value": 10010102,
          "label": "中心动态",
          "children": []
        }]
      }, {
        "value": 100102,
        "label": "政策法规",
        "children": [{
          "value": 10010201,
          "label": "省内政策",
          "children": []
        }, {
          "value": 10010202,
          "label": "国家政策",
          "children": []
        }, {
          "value": 10010203,
          "label": "地方政策",
          "children": []
        }]
      }, {
        "value": 100103,
        "label": "宣传培训",
        "children": [{
          "value": 10010301,
          "label": "培训动态",
          "children": []
        }]
      }, {
        "value": 100104,
        "label": "技术应用",
        "children": [{
          "value": 10010401,
          "label": "节约用电",
          "children": []
        }, {
          "value": 10010402,
          "label": "绿色用电",
          "children": []
        }, {
          "value": 10010403,
          "label": "环保用电",
          "children": []
        }, {
          "value": 10010404,
          "label": "智能用电",
          "children": []
        }, {
          "value": 10010405,
          "label": "有序用电",
          "children": []
        }]
      }, {
        "value": 100105,
        "label": "亚行贷款",
        "children": [{
          "value": 10010501,
          "label": "项目进展",
          "children": []
        }]
      }, {
        "value": 100106,
        "label": "电能服务",
        "children": [{
          "value": 10010601,
          "label": "服务模式",
          "children": []
        }]
      }, {
        "value": 100107,
        "label": "电力交易",
        "children": [{
          "value": 10010701,
          "label": "最新政策",
          "children": []
        }, {
          "value": 10010702,
          "label": "通知公告",
          "children": []
        }]
      }, {
        "value": 100108,
        "label": "需求响应",
        "children": [{
          "value": 10010801,
          "label": "需求响应",
          "children": []
        }]
      }, {
        "value": 100201,
        "label": "首页",
        "children": []
      }, {
        "value": 100202,
        "label": "政策法规",
        "children": []
      }, {
        "value": 100301,
        "label": "技术应用",
        "children": [{
          "value": 10030101,
          "label": "节约用电",
          "children": [{
            "value": 1003010101,
            "label": "典型案例",
            "children": []
          }]
        }, {
          "value": 10030102,
          "label": "绿色用电",
          "children": [{
            "value": 1003010201,
            "label": "典型案例",
            "children": []
          }]
        }, {
          "value": 10030103,
          "label": "环保用电",
          "children": [{
            "value": 1003010301,
            "label": "典型案例",
            "children": []
          }]
        }, {
          "value": 10030104,
          "label": "智能用电",
          "children": [{
            "value": 1003010401,
            "label": "典型案例",
            "children": []
          }]
        }, {
          "value": 10030105,
          "label": "有序用电",
          "children": [{
            "value": 1003010501,
            "label": "典型案例",
            "children": []
          }]
        }]
      }, {
        "value": 100302,
        "label": "电能服务",
        "children": [{
          "value": 10030201,
          "label": "典型案例",
          "children": []
        }]
      }, {
        "value": 100303,
        "label": "需求响应",
        "children": [{
          "value": 10030301,
          "label": "典型案例",
          "children": []
        }]
      }, {
        "value": 100401,
        "label": "宣传培训",
        "children": [{
          "value": 10040101,
          "label": "公益宣传",
          "children": []
        }, {
          "value": 10040102,
          "label": "培训资料",
          "children": []
        }]
      }, {
        "value": 100402,
        "label": "技术应用",
        "children": [{
          "value": 10040201,
          "label": "节约用电",
          "children": [{
            "value": 1004020101,
            "label": "技术资料",
            "children": []
          }, {
            "value": 1004020102,
            "label": "技术产品提供商",
            "children": []
          }]
        }, {
          "value": 10040202,
          "label": "绿色用电",
          "children": [{
            "value": 1004020201,
            "label": "技术资料",
            "children": []
          }, {
            "value": 1004020202,
            "label": "技术产品提供商",
            "children": []
          }]
        }, {
          "value": 10040203,
          "label": "环保用电",
          "children": [{
            "value": 1004020301,
            "label": "技术资料",
            "children": []
          }, {
            "value": 1004020302,
            "label": "技术产品提供商",
            "children": []
          }]
        }, {
          "value": 10040204,
          "label": "智能用电",
          "children": [{
            "value": 1004020401,
            "label": "技术资料",
            "children": []
          }, {
            "value": 1004020402,
            "label": "技术产品提供商",
            "children": []
          }]
        }, {
          "value": 10040205,
          "label": "有序用电",
          "children": [{
            "value": 1004020501,
            "label": "技术资料",
            "children": []
          }, {
            "value": 1004020502,
            "label": "技术产品提供商",
            "children": []
          }]
        }]
      }, {
        "value": 100403,
        "label": "亚行贷款",
        "children": [{
          "value": 10040301,
          "label": "贷款资料",
          "children": []
        }]
      }, {
        "value": 100404,
        "label": "DSM考核",
        "children": [{
          "value": 10040401,
          "label": "考核报告",
          "children": []
        }]
      }]
    }
}
```