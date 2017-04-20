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
         String firstName = "Test";
         String lastName = "User";
         Address address = new Address(123, "ABC", "Baltimore", "MD");
         Customer sampleCustomer = new Customer(firstName, lastName, address);
         CustomerLoginView clv = new CustomerLoginView(sampleCustomer);
         controller.addView(clv);
         ((MainView) controller.mainView).setCurrentView(clv);
         // TODO: Check username/password in model's database
         // TODO: Check if is an employee or customer
         // TODO: Send user to reservations
      }
   }
}
