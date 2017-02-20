
# MicroServiceFullInfo

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**serviceName** | **String** |  | 
**version** | **String** |  |  [optional]
**url** | **String** | Target Service URL,start with / | 
**protocol** | [**ProtocolEnum**](#ProtocolEnum) | Service Protocol | 
**visualRange** | [**VisualRangeEnum**](#VisualRangeEnum) | [visual Range]interSystem:0,inSystem:1 |  [optional]
**lbPolicy** | [**LbPolicyEnum**](#LbPolicyEnum) | lb policy |  [optional]
**nodes** | [**List&lt;NodeInfo&gt;**](NodeInfo.md) |  |  [optional]
**status** | **String** |  |  [optional]


<a name="ProtocolEnum"></a>
## Enum: ProtocolEnum
Name | Value
---- | -----
REST | &quot;REST&quot;
UI | &quot;UI&quot;
MQ | &quot;MQ&quot;
FTP | &quot;FTP&quot;
SNMP | &quot;SNMP&quot;
TCP | &quot;TCP&quot;
UDP | &quot;UDP&quot;


<a name="VisualRangeEnum"></a>
## Enum: VisualRangeEnum
Name | Value
---- | -----
_0 | &quot;0&quot;
_1 | &quot;1&quot;


<a name="LbPolicyEnum"></a>
## Enum: LbPolicyEnum
Name | Value
---- | -----
ROUND_ROBIN | &quot;round-robin&quot;
HASH | &quot;hash&quot;
LEAST_CONN | &quot;least_conn&quot;



