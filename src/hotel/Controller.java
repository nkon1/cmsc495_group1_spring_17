package hotel;
import view.View;
import view.LoginView;
import view.MainView;
import view.ViewType;
import view.StarterView;
import view.ReservationView;
import view.SelectRoomView;
import view.NewCustomerView;
import view.DetailsView;
import view.EmployeeLoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import customer.Address;
import customer.Customer;
import database.Payment;
import hotel.Reservation; //imported for testing
import hotel.Occupant;
import java.util.Date;
import room.ParadiseRoom;
import room.Room;
import room.StudioRoom;
import room.SuiteRoom;

public class Controller {
   private Model model;
   private View mainView;
   private List<View> views = new ArrayList<>();
   

   Controller(Model model, View mainView) {
      this.model = model;
      this.mainView = mainView;
      setView(((MainView)mainView).getCurrentView());
   }
   
   public void setView(View view) {
      views.add(view);
      // Lookup the view and add its listeners
      if(view.getViewType() == ViewType.STARTER) {
         StarterView starterView = (StarterView)view;
         starterView.addDoneButtonActionListener(new CustomerLoginDoneButtonListener(this));
         starterView.addViewReservationButtonListener(new ViewReservationButtonListener(this));
         starterView.addMakeReservationButtonListener(new MakeReservationButtonListener(this));
         
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
         
      } else if(view.getViewType() == ViewType.SELECT_ROOM) {
         SelectRoomView srv = (SelectRoomView)view;
         for(JButton button : srv.getSelectionButtons()) {
            srv.addSelectRoomActionListener(button, new SelectRoomListener(this));
         }
         
      } else if(view.getViewType() == ViewType.RESERVATION) {
         view = (ReservationView)view;
         
      } else if(view.getViewType() == ViewType.DETAILS) {
         DetailsView dv = (DetailsView)view;
         dv.addNextButtonActionListener(new DetailsViewListener(this));
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
         controller.setView(lv);
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
         controller.setView(lv);
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
         controller.setView(ncv);
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
         // TODO: Check username/password in model's database
         // TODO: Check if is an employee or customer
         // TODO: Send user to reservations
      }
   }
   
   private class ViewReservationButtonListener implements ActionListener {
       private Controller controller;
       
       ViewReservationButtonListener(Controller controller) {
           this.controller = controller;
       }

        @Override
        public void actionPerformed(ActionEvent e) {
           LoginView lv = new LoginView();
           lv.getNewCustomerButton().setEnabled(false);
           controller.setView(lv);
          ((MainView) controller.mainView).setCurrentView(lv);
          
        }
   }
   
   private class MakeReservationButtonListener implements ActionListener {
        private Controller controller;
        
        MakeReservationButtonListener(Controller controller) {
            this.controller = controller;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           SelectRoomView srv = new SelectRoomView(createTestRooms());
           controller.setView(srv);
          ((MainView) controller.mainView).setCurrentView(srv);
        }
    }
       
    private class SelectRoomListener implements ActionListener {
      private Controller controller;
       
      SelectRoomListener(Controller controller) {
         this.controller = controller;
      }
       
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         Room tempRoom = new ParadiseRoom();
         DetailsView dv = new DetailsView(tempRoom);
         controller.setView(dv);
         ((MainView) controller.mainView).setCurrentView(dv);
      }
       
    }
    
    private class DetailsViewListener implements ActionListener {
       private Controller controller;
       
       DetailsViewListener(Controller controller) {
          this.controller = controller;
       }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         LoginView lv = new LoginView();
         controller.setView(lv);
         ((MainView) controller.mainView).setCurrentView(lv);
      }
       
    }
    
    public Customer createTestCustomer(){
       String firstName = "Test";
         String lastName = "User";
         Address address = new Address("ABC", "Baltimore", "MD", 123);
         Customer sampleCustomer = new Customer(firstName, lastName, address);
         return sampleCustomer;
   }
   
   public Reservation createTestReservation(){
          Occupant testOccupant = new Occupant(createTestCustomer(), 2);
          ParadiseRoom testRoom = new ParadiseRoom();
          Payment testPay = new Payment("9999 9999 9999 9999", new Date(), createTestCustomer(), createTestCustomer().getAddresss());
          Date date = new Date();
          Reservation testReservation = new Reservation(testOccupant, testRoom, testPay, date, 2 );
          return testReservation;
   }
   
   public List<Room> createTestRooms() {
      ParadiseRoom pRoom = new ParadiseRoom();
      StudioRoom studioRoom = new StudioRoom();
      SuiteRoom suiteRoom = new SuiteRoom();
      List<Room> rooms = new ArrayList<>();
      rooms.add(pRoom);
      rooms.add(suiteRoom);
      rooms.add(studioRoom);
      return rooms;
   }
   
}
