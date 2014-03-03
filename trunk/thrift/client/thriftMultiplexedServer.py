#encoding=utf-8
#__author__ = 'liuzh'
from thrift.TMultiplexedProcessor import TMultiplexedProcessor
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket, TTransport
from com.thrift.test.service import PersonService, FileUploadService
from com.thrift.test.service.impl.FileUploadServiceImpl import FileUploadServiceImpl
from com.thrift.test.service.impl.PersonServiceImpl import PersonServiceImpl

handler = PersonServiceImpl()
personProcessor = PersonService.Processor(handler)

fileUploadHandler = FileUploadServiceImpl()
fileUploadProcessor = FileUploadService.Processor(fileUploadHandler)

processor = TMultiplexedProcessor()

processor.registerProcessor('PERSON_SERVICE', personProcessor)
processor.registerProcessor('FILE_UPLOAD_SERVICE', fileUploadProcessor)

transport = TSocket.TServerSocket(port=9988)
tfactory = TTransport.TBufferedTransportFactory()
pfactory = TBinaryProtocol.TBinaryProtocolFactory()

# server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

# You could do one of these for a multithreaded server
#server = TServer.TThreadedServer(processor, transport, tfactory, pfactory)
server = TServer.TThreadPoolServer(processor, transport, tfactory, pfactory)

print('Starting the server...')
server.serve()
print('done.')