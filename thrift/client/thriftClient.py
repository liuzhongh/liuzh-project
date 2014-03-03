#encoding=utf-8
#__author__ = 'liuzh'
from thrift import Thrift
from thrift.protocol import TBinaryProtocol
from thrift.transport import TSocket, TTransport
from com.thrift.test.bo.ttypes import Person
from com.thrift.test.service import PersonService

try:

    # Make socket
    transport = TSocket.TSocket('localhost', 9988)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    # Create a client to use the protocol encoder
    client = PersonService.Client(protocol)

    # Connect!
    transport.open()

    sum = client.getValue()
    print(sum)

    person = Person()

    person.name = 'name1'
    person.passwod = 'pwd'
    person.age = 12121212

    p = client.getPerson(person)

    print(p)
    # Close!
    transport.close()

except Thrift.TException as tx:
    print('%s' % (tx.message))