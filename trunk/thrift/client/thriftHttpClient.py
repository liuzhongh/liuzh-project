#encoding=utf-8
#__author__ = 'liuzh'
from thrift import Thrift
from thrift.protocol import TBinaryProtocol
from thrift.transport import TTransport, THttpClient
from com.thrift.test.service import PersonService

try:
    httpClient = THttpClient.THttpClient('http://127.0.0.1:80/thriftServer.php?srv=PersonServiceImpl')
    transport = TTransport.TBufferedTransport(httpClient)
    protocol = TBinaryProtocol.TBinaryProtocol(transport)
    client = PersonService.Client(protocol)

    transport.open()
    print(client.getValue())
    transport.close()
except Thrift.TException as tx:
    print('%s' % (tx.message))