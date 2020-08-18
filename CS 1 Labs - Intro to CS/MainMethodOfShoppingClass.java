 public static void main(String[] args){

  TestShoppingCart myShoppingCart = new TestShoppingCart();
  Scanner input = new Scanner(System.in);
  String answ ="";
  int option =3;

 // While method for the menu options.
  while(!answ.equals("exit")){
    System.out.println("\n1. Set client name ");
    System.out.println("2. Add tickets to the shopping cart");
    System.out.println("3. Checkout");
    System.out.print("Input your option : ");
    option = input.nextInt();
    input.nextLine();
    System.out.println("Option  selected: "+option);
    switch(option){
      //Option 1 will print the client name.
      case 1: System.out.print("\nClient name: " );
              String name = input.nextLine();
              myShoppingCart.setClientName(name);
              System.out.println("Hello " + myShoppingCart.getClientName());
              break;
      //Option 2 will create a new ticket, request the input of ticket information
      //and add the new ticket to the shopping cart with the method addToCart.
      case 2: System.out.print("\nEnter the event of the ticket : ");
              Ticket ticket = new Ticket();
              String event = input.nextLine();
		          ticket.setEvent(event);
              System.out.print("Enter the seat of the ticket : ");
              String seat = input.nextLine();
              ticket.setSeat(seat);
              System.out.print("Enter the price of the ticket : ");
              double price = input.nextDouble();
              ticket.setPrice(price);
              myShoppingCart.addToCart(ticket);
              break;//bug fixed

      // Option 3 will do the checktout by:
      // calculating the total of the shopping cart ,
      // printing the contents of the shopping cart and exiting the program.
      case 3: double total = myShoppingCart.calculateTotal();
		          System.out.print("Your final cart is: ");
		          myShoppingCart.printShoppingCart();
              System.out.println("Total: " + total);
		          answ = "exit";
              System.out.println("Good bye!");
		          break;
      default: System.out.println("Unrecognized input, try again");
   }
  }
 }