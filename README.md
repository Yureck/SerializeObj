# SerializeObj
Написать код, который сериализирует и десериализирует в/из файла все значения полей
класса, которые отмечены аннотацией @Save.

2) Нужно допилить задачу так, чтобы сериализация и десериализация работала с вложенными обьектами:

class A {
  @Save int a;
  @Save B b;
}

class B {
  @Save long c;
}
