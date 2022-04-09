package com.appletree24.hashtable;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable=new HashTable(7);
        String key="";
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("del:删除雇员");
            System.out.println("exit：退出系统");
            key=scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id=scanner.nextInt();
                    System.out.println("输入名字");
                    String name=scanner.next();
                    Emp emp=new Emp(id,name);
                    hashTable.add(emp);
                    break;
                case "del":
                    System.out.println("输入id");
                    id=scanner.nextInt();
                    hashTable.deleteEmp(id);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员ID");
                    id=scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                default:
                    break;
            }
        }
    }
}


class HashTable{
    private EmpLinkedList[] empLinkedListArray;
    private int size;
    public HashTable(int size){
        this.size=size;
        empLinkedListArray=new EmpLinkedList[size];
        for (int i=0;i<size;i++){
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        int empLinkedListNO=hashFun(emp.id);
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    public void list(){
        for(int i=0;i<size;i++){
            empLinkedListArray[i].list(i);
        }
    }


    public int hashFun(int id){
        return id%size;
    }

    public boolean findEmpById(int id){
        int empLinkedListNO=hashFun(id);
        Emp emp=empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp!=null){
            System.out.println("找到在"+(empLinkedListNO+1)+"链表中"+","+"雇员ID为："+id);
            return true;
        }else{
            System.out.println("哈希表中不存在此雇员");
            return false;
        }
    }


    public void deleteEmp(int id){
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById_DeVer(id);
        if (emp.next == null) {
            empLinkedListArray[empLinkedListNO].head=null;
        } else {
            if (emp == null) {
                System.out.println("查无此人");
            } else {
                if (emp.next.next != null) {
                    System.out.println("删除成功");
                    emp.next = emp.next.next;
                } else {
                    System.out.println("删除成功");
                    emp.next = null;
                }
            }
        }
    }
}



class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    public Emp head;
    //添加第一个成员
    public void add(Emp emp){
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个雇员，使用辅助指针，定位到最后
        Emp curEmp=head;
        while (true){
            if(curEmp.next==null){
                break;
            }else{
                curEmp=curEmp.next;
            }
        }
        curEmp.next=emp;
    }

    public void list(int no){
        if(head==null){
            System.out.println("链表"+(no+1)+"为空");
            return;
        }
        System.out.print((no+1)+"链表的信息为：");
        Emp curEmp=head;
        while (true){
            System.out.printf("=>id=%d name=%s",curEmp.id,curEmp.name);
            if(curEmp.next==null){   //最后一个节点
                break;
            }
            curEmp=curEmp.next;
        }
        System.out.println();
    }


    public Emp findEmpById(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp=head;
        while (true){
            if(curEmp.id==id){
                break;
            }
            if(curEmp.next==null){
                curEmp=null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }

   //传入要删除结点的上一个节点
    public boolean deleteEmp(Emp emp){
        if(head==null){
            System.out.println("链表为空，无需删除");
            return false;
        }else if(emp==null){
            System.out.println("参数错误");
            return false;
        }
        emp.next=emp.next.next;
        return true;
    }


    public Emp findEmpById_DeVer(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }

        if(head.id==id){
            return head;
        }

        Emp curEmp=head;
        while(curEmp.next!=null){
            if(curEmp.next.id==id){
                return curEmp;
            }
            curEmp=curEmp.next;
        }
        return null;
    }
}
