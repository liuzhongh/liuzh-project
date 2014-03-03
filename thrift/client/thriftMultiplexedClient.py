#encoding=utf-8
#__author__ = 'liuzh'
from thrift import Thrift
from thrift.protocol import TBinaryProtocol
from thrift.protocol.TMultiplexedProtocol import TMultiplexedProtocol
from thrift.transport import TSocket, TTransport
from com.thrift.test.bo.ttypes import Person
from com.thrift.test.service import PersonService, FileUploadService

try:
    # Make socket
    transport = TSocket.TSocket('localhost', 9988)

    # Buffering is critical. Raw sockets are very slow
    transport = TTransport.TBufferedTransport(transport)

    # Wrap in a protocol
    protocol = TBinaryProtocol.TBinaryProtocol(transport)

    # Create a client to use the protocol encoder
    processor = TMultiplexedProtocol(protocol, 'PERSON_SERVICE')

    client = PersonService.Client(processor)

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

    fileUploadProcessor = TMultiplexedProtocol(protocol, 'FILE_UPLOAD_SERVICE')

    fileUploadClient = FileUploadService.Client(fileUploadProcessor)

    #定义服务端文件名和保存路径
    filename = "/home/liuzh/sample/CalService.thrift"

    #本地文件路径
    f = open("/home/liuzh/图片/2013-12-26 12:45:16的屏幕截图.png", "rb")

    content = f.read()
    f.close()
    fileUploadClient.transFile(content)

    # Close!
    transport.close()

except Thrift.TException as tx:
    print('%s' % (tx.message))