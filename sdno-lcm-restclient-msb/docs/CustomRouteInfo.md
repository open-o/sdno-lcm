
# CustomRouteInfo

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**serviceName** | **String** |  | 
**url** | **String** | Target Service URL,start with / | 
**control** | [**ControlEnum**](#ControlEnum) | [control Range] 0ï¼šdefault   1ï¼šreadonly  2ï¼šhidden  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) | [status] 1ï¼šabled    0ï¼šdisabled  |  [optional]
**visualRange** | [**VisualRangeEnum**](#VisualRangeEnum) | [visual Range]interSystem:0,inSystem:1 |  [optional]
**useOwnUpstream** | [**UseOwnUpstreamEnum**](#UseOwnUpstreamEnum) | [LB Policy]non_ip_hash:0,ip_hash:1 |  [optional]
**servers** | [**List&lt;RouteServer&gt;**](RouteServer.md) |  | 


<a name="ControlEnum"></a>
## Enum: ControlEnum
Name | Value
---- | -----
_0 | &quot;0&quot;
_1 | &quot;1&quot;
_2 | &quot;2&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
_0 | &quot;0&quot;
_1 | &quot;1&quot;


<a name="VisualRangeEnum"></a>
## Enum: VisualRangeEnum
Name | Value
---- | -----
_0 | &quot;0&quot;
_1 | &quot;1&quot;


<a name="UseOwnUpstreamEnum"></a>
## Enum: UseOwnUpstreamEnum
Name | Value
---- | -----
_0 | &quot;0&quot;
_1 | &quot;1&quot;



