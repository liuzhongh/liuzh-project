from django.shortcuts import render, render_to_response

# Create your views here.
from thrift.protocol import TBinaryProtocol
from thrift.protocol.TMultiplexedProtocol import TMultiplexedProtocol
from thrift.transport import TSocket, TTransport
import time
from com.thrift.test.bo.ttypes import Person
from com.thrift.test.service import PersonService


def index(request):

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

    person = Person()

    person.name = 'name1'
    person.passwod = 'pwd'
    person.age = 12121212

    psr = client.getPerson(person)
    dt = time.time()

    transport.close()
    return render_to_response('index.html', locals())