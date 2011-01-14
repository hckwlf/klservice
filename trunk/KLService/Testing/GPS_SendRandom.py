import sys
import time
import os
import socket

#change as you go:
#----------------
#The Android SDK platform-tools folder:
sdkFolder = "C:\\android-sdk-windows\\platform-tools\\"
adbApp = sdkFolder + "adb.exe"
fastbootApp = sdkFolder + "fastboot.exe"
#host of emulator/device:
devHost = "localhost"
#Port to use:
devPort = 5554

#Do not touch these settings:
recvBufferSize = 1000

###### Test SDK existence ######
sys.stdout.write("Trying to locate platform-tools folder:\n")
sys.stdout.write("\t[*] adb.exe location...")
if (os.path.isfile(adbApp)):
    sys.stdout.write(" Success...\n")
else:
    sys.stdout.write(" FAILED!...\n")
    sys.stdout.write("---------------------------------------------------\n")
    sys.stdout.write("Please change sdkFolder variable inside this app...\n")
    sys.stdout.write("---------------------------------------------------\n")
    sys.exit(1)

sys.stdout.write("\t[*] flashboot.exe location...")
if (os.path.isfile(fastbootApp)):
    sys.stdout.write(" Success...\n")
else:
    sys.stdout.write(" FAILED!...\n")
    sys.stdout.write("---------------------------------------------------\n")
    sys.stdout.write("Please change sdkFolder variable inside this app...\n")
    sys.stdout.write("---------------------------------------------------\n")
    sys.exit(1)

###### Connection to emulator ######
sys.stdout.write("Trying to connect emulator\device " + devHost + "@" + str(devPort) + ":\n")
soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

sys.stdout.write("\t[*] Connecting to emulator\device...")
try:
    soc.connect((devHost, devPort))
except:
    sys.stdout.write(" FAILED...\n")
    sys.stdout.write("----------------------------------------------------\n")
    sys.stdout.write("Please change host/port variables inside this app...\n")
    sys.stdout.write("\t(Is emulator running??)\n")
    sys.stdout.write("----------------------------------------------------\n")
    sys.exit(1)
    
sys.stdout.write(" Success...\n")

###### Talk Emulator protocol ######
sys.stdout.write("\t[*] Talking to emulator...")
data = soc.recv(recvBufferSize)
data = soc.recv(recvBufferSize)
if (data.find("OK") != -1):
    sys.stdout.write(" Success...\n")
else:
    sys.stdout.write(" FAILED...\n")
    sys.stdout.write("------------------------------------------------------\n")
    sys.stdout.write("Something went wrong, this is not emulator protocol...\n")
    sys.stdout.write("------------------------------------------------------\n")
    sys.exit(1)


###### Data transfer ######
sys.stdout.write("Trying to locate platform-tools folder:\n")

sys.stdout.write("\t[*] Sending 'geo fix 30000222 35022002'")
soc.send("geo fix 30000223 35022002\n")
data = soc.recv(recvBufferSize)
if (data.find("OK") != -1):
    sys.stdout.write(" Success...\n")
else:
    sys.stdout.write(" FAILED...\n")
    sys.stdout.write("\t\tGeo command did not received as expected...\n")
    sys.exit(1)

sys.stdout.write("========[ Exiting ]========\n")
soc.close()
sys.stdout.write("Connection closed...\n")

sys.exit(0)


