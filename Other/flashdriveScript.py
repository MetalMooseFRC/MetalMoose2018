import shutil, errno, datetime

src = ""
dst = "D:/" + datetime.datetime.now().strftime("%Y-%m-%d_%H-%M-%S")

try:
    shutil.copytree(src, dst)
except OSError as exc:
    if exc.errno == errno.ENOTDIR:
        shutil.copy(src, dst)
    else: raise
