include "Person.thrift"

namespace java com.thrift.test
namespace php com.thrift.test

service PersonService{
	string getValue();
	Person.Person getPerson(1: Person.Person person);
}

