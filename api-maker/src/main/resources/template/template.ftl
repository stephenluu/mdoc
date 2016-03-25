# 一、接口
<#list apis as api>
## ${api_index + 1} : ${api.apiName}

### 描述
${api.methodComment}

### 签名
`${api.methodSign}`

### 参数

|名称|类型|描述|示例|
|---| ---| ---|---|
<#list api.args as arg>
<#list arg.fields as row>
|${row.field} |${row.type}|${row.des}||
</#list>
</#list>

### 返回值

|名称|类型|描述|示例|
|---| ---| ---|---|
<#list api.returnType.fields as row>
|${row.field} |${row.type}|${row.des}||
</#list>

</#list>

# 二、字段表
<#list pojos as pojo>
### ${pojo.name}
|名称|类型|描述|示例|
|---| ---| ---|---|
<#list pojo.fields as row>
|${row.field} |${row.type}|${row.des}||
</#list>
</#list>