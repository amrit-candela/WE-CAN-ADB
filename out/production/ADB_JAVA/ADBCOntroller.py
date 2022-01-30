import subprocess
import sys
import os
import time


def getDeviceList():
    x = os.popen("adb devices").read().split()
    ls = []
    for i in x:
        if any(map(str.isdigit, i)):
            ls.append(i)
    return ls


def reboot_all():
    device_list = getDeviceList()
    for device in device_list:
        reboot(device)


def reboot(device_name):
    os.system("adb reboot " + device_name)


def install_app_all_phone(app_dir):
    device_list = getDeviceList()
    for device in device_list:
        install_app(app_dir, device)


def install_app(app_dir, device_name):
    os.system("adb -s " + device_name + "  install -t " + app_dir)
    print("Installation Success on Phone " + device_name)


def uninstall_app_all(app_name):
    device_list = getDeviceList()
    for device in device_list:
        os.system("adb -s " + device + " shell pm uninstall " + app_name)


def uninstall_app(device_name, app_name):
    os.system("adb -s " + device_name + " shell pm uninstall " + app_name)
    print("Uninstallation Success on Phone " + device_name)


def open_app_in_all_phone(app_name_module):
    # adb - s RZ8R82G4F8Y shell monkey -p com.candela.wecan 1
    device_list = getDeviceList()
    for device in device_list:
        os.system("adb -s " + device + " shell monkey -p " + app_name_module)


def open_app(device_name, app_name_module):
    subprocess.call("adb -s " + device_name + " shell monkey -p " + app_name_module + " 1", shell=True)


def enter_home(device_name):
    subprocess.call("adb -s " + device_name + " shell input keyevent 66", shell=True)
    subprocess.call("adb -s " + device_name + " shell input keyevent 0", shell=True)


def input_data_into_app(device_name, data):
    # subprocess.run("adb -s " + device_name + " shell input keyevent KEYCODE_MOVE_END", shell=True, check=True)
    # subprocess.run("adb -s " + device_name + " shell input keyevent KEYCODE_MOVE_END", shell=True, check=True)
    # time.sleep(1)
    # subprocess.run("adb -s " + device_name + " shell input keyevent --longpress $(printf 'KEYCODE_DEL %.0s' {1..50})", shell=True, check=True)
    # time.sleep(10)
    # subprocess.Popen("adb -s " + device_name + " shell input keyevent KEYCODE_CLEAR", shell=True)
    # subprocess.Popen("adb shell input keyevent KEYCODE_DEL", shell=True)
    # subprocess.Popen("adb -s " + device_name + " shell input keyevent 28", shell=True)
    # subprocess.Popen("adb shell input keyevent 61", shell=True)
    # subprocess.Popen("adb -s " + device_name + " shell input keyevent 28", shell=True)
    # subprocess.Popen("adb shell input keyevent 61", shell=True)
    # time.sleep(3)
    subprocess.Popen("adb -s " + device_name + " shell input text 'Amrit%sRaj'", shell=True)
    time.sleep(3)
    subprocess.Popen("adb shell input keyevent 61", shell=True)
    subprocess.Popen("adb -s " + device_name + " shell input text '192.168.200.11'", shell=True)
    time.sleep(3)
    subprocess.Popen("adb shell input keyevent 61", shell=True)
    subprocess.Popen("adb -s " + device_name + " shell input text 'Test'", shell=True)
    time.sleep(3)
    subprocess.Popen("adb -s " + device_name + " shell input keyevent 66", shell=True)
    subprocess.Popen("adb -s " + device_name + " shell input keyevent 0", shell=True)


def grant_permisstion(device_name, app_packeage_name):
    # os.popen("adb -s shell dumpsys package " + app_packeage_name + " | grep android.permission")
    os.popen(
        "adb -s " + device_name + " shell pm grant " + app_packeage_name + " android.permission.ACCESS_FINE_LOCATION")
    os.popen(
        "adb -s " + device_name + " shell pm grant " + app_packeage_name + " android.permission.WRITE_EXTERNAL_STORAGE")
    os.popen(
        "adb -s " + device_name + " shell pm grant " + app_packeage_name + " android.permission.READ_EXTERNAL_STORAGE")
    os.popen("adb -s " + device_name + " shell input keyevent 0")


def take_screen_shot(device_name):
    # subprocess.Popen("adb -s " + device_name + " exec-out screencap -p > shots/" + device_name + ".png", shell=True)
    subprocess.Popen("adb -s " + device_name + " exec-out screencap -p > shots/" + device_name + ".png", shell=True)


def swipe_lef():
    os.popen("adb shell input touchscreen swipe 126 459 413 472")


def stream_phone_screen(device_name):
    try:
        subprocess.call(
            "adb -s " + device_name + " exec-out screenrecord --output-format=h264 - | ffplay -framerate 60 -probesize 32 -sync video  -",
            shell=True)
    except KeyboardInterrupt:
        print("KeyboardInterrupt by python")

# print(getDeviceList())
# uninstall_app("RZ8R82G4F8Y", "com.candela.wecan")
# install_app("/home/amrit-candela/Desktop/WE-CAN\ APP/we-can/app/build/outputs/apk/debug/app-debug.apk", "RZ8R82G4F8Y")
# grant_permisstion("RZ8R82G4F8Y", "com.candela.wecan")
# open_app("RZ8R82G4F8Y", "com.candela.wecan")
# input_data_into_app("RZ8R82G4F8Y", "data")
# enter_home("RZ8R82G4F8Y")
# time.sleep(5)
# take_screen_shot("RZ8R82G4F8Y")
