namespace java com.thrift.test
namespace js com.thrift.test

const string SERVICE_NAME = "divService";

service DivService {
	i32 div(1:i32 param1, 2:i32 param2)
}