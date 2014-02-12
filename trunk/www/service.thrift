include "Person.thrift"

namespace java com.thrift.test.service
namespace php com.thrift.test.service

service PersonService{
	string getValue();
	Person.Person getPerson(1: Person.Person person);
}

