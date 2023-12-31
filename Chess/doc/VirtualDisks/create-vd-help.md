Как создать виртуальные диски на флешке
=======================================

Содержимое пакетного исполняемого файла `create-vd-home.bat`  
для работы с Eclipse на **домашнем компьютере** используя флешку.  

```bat 
SUBST Z: /D
SUBST Z: F:/eclipse2019-06
SUBST Y: /D
SUBST Y: F:/eclipse-workspaces
pause
```

Предположим что ваша флешка, вставленная в домашний компьютер, имеет имя **F**.   

Команда `SUBST Z: /D` удаляет старый виртуальный диск **Z**, если он есть.   
Команда `SUBST Z: F:/eclipse2019-06` создает новый виртуальный диск **Z**  
соответствующий папке где расположен _eclipse_ версии 2019-06. 

Команда `SUBST Y: /D` удаляет старый виртуальный диск **Y**, если он есть.   
Команда `SUBST Y: F:/eclipse-workspaces` создает новый виртуальный диск **Y**  
соответствующий папке где расположены рабочие пространства _eclipse_  
с проектами внутри рабочих пространств.

Если на **рабочем компьютере** вставленная флешка будет иметь имя **E**,  
то для нее создается отдельный файл `create-vd-work.bat` 

Настройки для рабочих пространств и среды _Eclipse_ будут сохраняться   
на виртуальных дисках **Y** и **Z** при переносе флешки на разные компьютеры.