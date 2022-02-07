from pymouse import PyMouse
import win32api, win32con
import time
time.sleep(5)
m=PyMouse()
for x in range(10000):
    time.sleep(2)
    m.click(8888,8888)
    print("click")



