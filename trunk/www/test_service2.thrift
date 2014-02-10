namespace php com.test.service

struct Person {
    1:string name;
    2:i32 age;
    3:string password
}
service TestThriftServiceTwo {
	string test1(1:string value1);
	Person testPerson(1:Person person);
}

