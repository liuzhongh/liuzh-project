#encoding=utf-8
#__author__ = 'liuzh'
import time


class FileUploadServiceImpl:
    def transFile(self, content):
        fileName = 'rre.png'
        f = open(fileName, "wb")
        f.write(content)
        f.close()
        print(content)