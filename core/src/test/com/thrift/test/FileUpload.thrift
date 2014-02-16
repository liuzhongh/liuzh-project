namespace java com.thrift.test.service
namespace php com.thrift.test.service
namespace py com.thrift.test.service

service FileUploadService {
	void transFile(1: binary content);
}