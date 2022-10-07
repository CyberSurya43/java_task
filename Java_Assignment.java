import java.util.*;
import java.io.*;
class Account implements Serializable{
	protected int accno;
	protected String accholder;
	int pin_no;
	int balance;
	public Account(int accno, String accholder, int pin_no, int balance) {
		super();
		this.accno = accno;
		this.accholder = accholder;
		this.pin_no = pin_no;
		this.balance = balance;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getAccholder() {
		return accholder;
	}
	public void setAccholder(String accholder) {
		this.accholder = accholder;
	}
	public int getPin_no() {
		return pin_no;
	}
	public void setPin_no(int pin_no) {
		this.pin_no = pin_no;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return  accno + "\t\t" + accholder + "\t "
				+ pin_no + "\t" + balance ;
	}


}
class AccData implements java.io.Serializable{

	static List<Account>acc=new ArrayList<>();
	public AccData(){
		Account a=new Account(101,"Suresh",2343,25234);
		Account b=new Account(102,"Ganesh",5432,34123);
		Account c=new Account(103,"Magesh",7854,26100);
		Account d=new Account(104,"Naresh",2345,80000);
		Account e=new Account(105,"Harish",1907,103400);
		acc.add(a);
		acc.add(b);
		acc.add(c);
		acc.add(d);
		acc.add(e);
	}



	@Override
	public String toString() {
		return super.toString();
	}



	public static void   cusdetails() {
		System.out.println("Account_number\t Name\t pin \tbalance");
		for(Account a:acc)
		{
			System.out.println(a.toString());
		}

	}
	public static void checkbalance(int accnum,int pin) {
		for(Account a:acc) {
			if(a.accno==accnum) {
				System.out.println(a.getBalance());
				break;
			}
		}
	}
	public static void  withdraw(int accnumber,int amount,int pin) {
		for(Account a:acc) {
			if(a.accno==accnumber) {
				if(a.balance>amount) {
					a.balance=a.balance-amount;
					System.out.println("after withdraw"+a.balance);
				}
				else {
					System.out.println("Insufficient Balance ");
				}
			}
		}
	}
	public static boolean checkpin(int accnum,int pin) {
		for(Account a:acc) {
			if(a.accno==accnum&&a.pin_no==pin) {
				return true;
			}
		}
		return false;}

	public static void transfer(int accnum,int totransfer,int amount) {
		int flag=0;
		for(Account a:acc) {
			if(a.accno==accnum) {
				if(a.balance>amount) {
					a.balance=a.balance-amount;
					flag=1;
				}

				else {
					System.out.println("Insufficient Balance ");
				}
			}
		}
		if(flag==1) {
			for(Account b:acc) {
				if(b.accno==totransfer) {
					b.balance=b.balance+amount;}
			}
		}
	}
	public static void serial()  {
		try {

			FileOutputStream fil = new FileOutputStream("D:\\ATMDEMO.txt");
			ObjectOutputStream out = new ObjectOutputStream(fil);
			out.writeObject(acc);
			out.close();
			fil.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void deserial() throws ClassNotFoundException {
		try {
			FileInputStream fileIn = new FileInputStream("D:\\ATMDEMO.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}


public class AtmDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		Scanner sc =new Scanner(System.in);
		AccData Acc=new AccData();
		while(true) {

			System.out.println("Enter 1 to load cash");
			System.out.println("Enter 2 to view customerdetails");
			System.out.println("Enter 3 to transfer or withdraw or checkbalance");
			System.out.println("Enter the option:");
			int option=sc.nextInt();
			switch(option) {
			case 1:
				System.out.println("Enter the Denominations");
				System.out.println("2000 = ");
				int d1=sc.nextInt();
				System.out.println("500 = ");
				int d2=sc.nextInt();
				System.out.println("100 = ");
				int d3=sc.nextInt();
				break;
			case 2:
				Acc.cusdetails() ;
				Acc.serial();
				Acc.deserial();
				break;
			case 3:
				System.out.println("1 to check balance:");
				System.out.println("2 to Withdraw:");
				System.out.println("3 to transfer:");
				System.out.println("Enter the enquiry option:");
				int enquiry=sc.nextInt();
				System.out.println("Enter the account number:");
				int accnum=sc.nextInt();
				System.out.println("Enter the pin:");
				int pin=sc.nextInt();
				if(Acc.checkpin(accnum,pin)) {
					switch(enquiry) {
					case 1:
						System.out.println("BALANCE IS:");
						Acc.checkbalance(accnum,pin);
						Acc.serial();
						Acc.deserial();
						break;
					case 2:
						System.out.println("Enter the amount to withdraw:");
						int amount=sc.nextInt();
						Acc.withdraw(accnum,amount,pin);
						Acc.serial();
						Acc.deserial();
						break;
					case 3:
						System.out.println("Enter the accont number to transfer: ");
						int totransfer=sc.nextInt();
						System.out.println("Enter the amount  to transfer: ");
						int amounttotransfer=sc.nextInt();
						Acc.transfer(accnum,totransfer,amounttotransfer);
						Acc.serial();
						Acc.deserial();
						break;
					}
				}
				else {
					System.out.println("incorect pin");
				}

				break;
			case 4:
				break;


			}
		}
	}
}
