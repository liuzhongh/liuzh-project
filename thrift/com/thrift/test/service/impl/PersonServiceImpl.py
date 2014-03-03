#encoding=utf-8
#__author__ = 'liuzh'
from com.thrift.test.bo.ttypes import Person
from com.thrift.test.service import PersonService

class PersonServiceImpl:
    def getValue(self):
        return  'it is test for python'
    def getPerson(self, person):
        print(person)
        return person
