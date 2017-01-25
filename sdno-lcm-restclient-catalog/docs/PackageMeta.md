
# PackageMeta

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**csarId** | **String** |  |  [optional]
**name** | **String** |  |  [optional]
**downloadUri** | **String** |  |  [optional]
**size** | **String** |  |  [optional]
**version** | **String** |  |  [optional]
**provider** | **String** |  |  [optional]
**type** | **String** |  |  [optional]
**format** | **String** |  |  [optional]
**deletionPending** | **Boolean** |  |  [optional]
**createTime** | **String** |  |  [optional]
**modifyTime** | **String** |  |  [optional]
**operationalState** | [**OperationalStateEnum**](#OperationalStateEnum) |  |  [optional]
**usageState** | [**UsageStateEnum**](#UsageStateEnum) |  |  [optional]
**onBoardState** | **String** |  |  [optional]
**processState** | [**ProcessStateEnum**](#ProcessStateEnum) |  |  [optional]


<a name="OperationalStateEnum"></a>
## Enum: OperationalStateEnum
Name | Value
---- | -----
ENABLED | &quot;Enabled&quot;
DISABLED | &quot;Disabled&quot;


<a name="UsageStateEnum"></a>
## Enum: UsageStateEnum
Name | Value
---- | -----
INUSE | &quot;InUse&quot;
NOTINUSE | &quot;NotInUse&quot;


<a name="ProcessStateEnum"></a>
## Enum: ProcessStateEnum
Name | Value
---- | -----
NORMAL | &quot;normal&quot;
ONBOARDING | &quot;onBoarding&quot;
ONBOARDFAILED | &quot;onBoardFailed&quot;
DELETING | &quot;deleting&quot;
DELETEFAILED | &quot;deleteFailed&quot;



