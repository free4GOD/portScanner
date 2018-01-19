import sys
import socket
import re
from datetime import datetime


while True:
    initialIp = input("Enter the initial IP\n")
    if not re.search(r'^(\d+\.){3}\d+$', initialIp) or not all(int(b) in range(256) for b in initialIp.split('.')):
        print("The IP is incorrect. Try again\n")
    else:
        break
while True:
    finalIp = input("Enter the final IP\n")
    if not re.search(r'^(\d+\.){3}\d+$', finalIp) or not all(int(b) in range(256) for b in finalIp.split('.')):
        print("The IP is incorrect. Try again\n")
    else:
        break
while True:
    initialPort = input("Enter the initial port\n")
    if not re.search("^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$", initialPort):
        print("The port is incorrect. Try again\n")
    else:
        break
while True:
    finalPort = input("Enter the final port\n")
    if not re.search("^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$", finalPort):
        print("The port is incorrect. Try again\n")
    else:
        break



print("Please wait while scanning...")
initialIpSplitted = initialIp.split(".")
finalIpSplitted = [int(b) for b in finalIp.split('.')]
incrementalNum0 = int(initialIpSplitted[0])
incrementalNum1 = int(initialIpSplitted[1])
incrementalNum2 = int(initialIpSplitted[2])
incrementalNum3 = int(initialIpSplitted[3])
timeStart = datetime.now()
try:
    for i in range(incrementalNum0, finalIpSplitted[0]+1):
        for j in range(incrementalNum1, finalIpSplitted[1]+1):
            for k in range(incrementalNum2, finalIpSplitted[2]+1):
                for l in range(incrementalNum3, finalIpSplitted[3]+1):
                    ip = "%d.%d.%d.%d" % (i, j, k, l)
                    for port in range(int(initialPort),int(finalPort)):  
                        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                        result = sock.connect_ex((str(i)+"."+str(j)+"."+str(k)+"."+str(l), port))
                        if result == 0:
                            sock.close()
                            print("IP: "+str(i)+"."+str(j)+"."+str(k)+"."+str(l)+" Port OPEN: "+str(port))
except KeyboardInterrupt:
    print("You pressed Ctrl+C")
    sys.exit()
timeEnd = datetime.now()
passedTime = timeEnd - timeStart
print('Scanning Completed in:', passedTime)
