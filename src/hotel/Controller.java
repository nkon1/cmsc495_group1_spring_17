package hotel;
import view.View;
import view.LoginView;
import view.MainView;
import view.ViewType;
import view.CustomerLoginView;
import view.ReservationView;
import view.NewCustomerView;
import view.EmployeeLoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import customer.Address;
import customer.Customer;
import hotel.Reservation; //imported for testing
import hotel.Occupant;
import java.util.Date;
import room.ParadiseRoom;
import room.Room;

public class Controller {
   private Model model;
   private View mainView;
   private List<View> views = new ArrayList<>();
   

   Controller(Model model, View mainView) {
      this.model = model;
      this.mainView = mainView;
      addView(((MainView)mainView).getCurrentView());
   }
   
   public void addView(View view) {
      views.add(view);
      // Lookup the view and add its listeners
      if(view.getViewType() == ViewType.CUSTOMER_LOGIN) {
         CustomerLoginView clv = (CustomerLoginView)view;
         clv.addDoneButtonActionListener(new CustomerLoginDoneButtonListener(this));
         clv.addViewReservationButtonListener(new ViewReservationButtonListener(this));
         // Add functionality for the make reservation button
         // Add functionality for the view reservation button
         
      } else if(view.getViewType() == ViewType.NEW_CUSTOMER) {
         NewCustomerView ncv = (NewCustomerView)view;
         ncv.addCancelButtonListener(new NewCustomerCancelButtonListener(this));
         ncv.addSubmitButtonListener(new NewCustomerSubmitButtonListener(this));
         
      } else if(view.getViewType() == ViewType.EMPLOYEE_LOGIN) {
         view = (EmployeeLoginView)view;
         
      } else if(view.getViewType() == ViewType.LOGIN) {
         LoginView lv = (LoginView)view;
         lv.addLoginNewCustomerButtonListener(new LoginNewCustomerButtonListener(this));
         lv.addLoginButtonListener(new LoginButtonListener(this));
         
      } else if(view.getViewType() == ViewType.RESERVATION) {
         view = (ReservationView)view;
      }
   }
   
   private class CustomerLoginDoneButtonListener implements ActionListener {
      private Controller controller;
      
      CustomerLoginDoneButtonListener(Controller controller) {
         this.controller = controller;
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         LoginView lv = new LoginView();
         controller.addView(lv);
         ((MainView) controller.mainView).setCurrentView(lv);
      }
   }
   
   private class NewCustomerCancelButtonListener implements ActionListener {
      private Controller controller;
      
      NewCustomerCancelButtonListener(Controller controller) {
         this.controller = controller;
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         LoginView lv = new LoginView();
         controller.addView(lv);
         ((MainView) controller.mainView).setCurrentView(lv);
      }
   }
   
   private class NewCustomerSubmitButtonListener implements ActionListener {
      private Controller controller;
      
      NewCustomerSubmitButtonListener(Controller controller) {
         this.controller = controller;
      }

      @Override
      public void actionPerformed(ActionEvent e) {
          
         // TODO Auto-generated method stub
         // TODO: Use model to write changes to database and send user back to login view
      }
   }
   
   private class LoginNewCustomerButtonListener implements ActionListener {
      private Controller controller;
      
      LoginNewCustomerButtonListener(Controller controller) {
         this.controller = controller;
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         NewCustomerView ncv = new NewCustomerView();
         controller.addView(ncv);
         ((MainView) controller.mainView).setCurrentView(ncv);
         
      }
   }
   
   private class LoginButtonListener implements ActionListener {
      private Controller controller;
      
      LoginButtonListener(Controller controller) {
         this.controller = controller;
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
        /* String firstName = "Test";
         String lastName = "User";
         Address address = new Address(123, "ABC", "Baltimore", "MD");
         Customer sampleCustomer = new Customer(firstName, lastName, address);*/
         CustomerLoginView clv = new CustomerLoginView(createTestCustomer());
         controller.addView(clv);
         ((MainView) controller.mainView).setCurrentView(clv);
         // TODO: Check username/password in model's database
         // TODO: Check if is an employee or customer
         // TODO: Send user to reservations
      }
   }
   
   public Customer createTestCustomer(){
       String firstName = "Test";
         String lastName = "User";
         Address address = new Address(123, "ABC", "Baltimore", "MD");
         Customer sampleCustomer = new Customer(firstName, lastName, address);
         return sampleCustomer;
   }
   
   private class ViewReservationButtonListener implements ActionListener {
       private Controller controller;
       
       
       
       ViewReservationButtonListener(Controller controller) {
           this.controller = controller;
           
                     
       }

        @Override
        public void actionPerformed(ActionEvent e) {
          ReservationView rv = new ReservationView();
          Occupant testOccupant = new Occupant(createTestCustomer(), 2);
          ParadiseRoom testRoom = new ParadiseRoom();
          Date date = new Date();
          Reservation testReservation = new Reservation(testOccupant, testRoom, date, 2 );
          rv.addReservation(testReservation);
          controller.addView(rv);
          ((MainView) controller.mainView).setCurrentView(rv);
          
          System.out.println(testReservation.getOccupant().toString());
        }
       
       
   }
}
