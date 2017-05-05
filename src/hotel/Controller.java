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

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import customer.Customer;
import customer.Payment;
import room.ParadiseRoom;
import room.Room;
import room.RoomType;
import room.StudioRoom;
import room.SuiteRoom;

/*
 * Edited 04/30/2017 by Tiff
 * 		-added dao method to submitButtonListener
 */

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
         ncv.addSubmitButtonListener(new NewCustomerSubmitButtonListener(ncv));
         
      } else if(view.getViewType() == ViewType.LOGIN) {
         LoginView lv = (LoginView)view;
         lv.addLoginNewCustomerButtonListener(new LoginNewCustomerButtonListener(this));
         lv.addLoginButtonListener(new LoginButtonListener(this, lv, createReservation()));
         
      } else if(view.getViewType() == ViewType.SELECT_ROOM) {
         SelectRoomView srv = (SelectRoomView)view;
         for(JButton button : srv.getSelectionButtons()) {
            srv.addSelectRoomActionListener(button, new SelectRoomListener(this));
         }
         
      } else if(view.getViewType() == ViewType.RESERVATION) {
         view = (ReservationView)view;
         
      } else if(view.getViewType() == ViewType.DETAILS) {
         DetailsView dv = (DetailsView)view;
         dv.addNextButtonActionListener(new DetailsViewNextButtonListener(this, dv));
         
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
      private NewCustomerView view;
      
      NewCustomerSubmitButtonListener(NewCustomerView view) {
         this.view = view;
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
          
         // TODO Auto-generated method stub
         Customer customer = new Customer(view.getFirstName(),
                                          view.getLastName(),
                                          view.getPassword(),
                                          view.getEmail());
         boolean added = model.addCustomer(customer);
         if(added) {
            JOptionPane.showMessageDialog(view, String.format("%s, %s was create successfully!", customer.getLastName(), customer.getFirstName()));
            view.getCancelButton().setText("Done");
         } else {
            view.displayErrorMessage("Problem encountered while attempting to create the account.");
         }
      }
   }
   
   private class LoginNewCustomerButtonListener implements ActionListener {
      private Controller controller;
      
      LoginNewCustomerButtonListener(Controller controller) {
         this.controller = controller;
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
         NewCustomerView ncv = new NewCustomerView();
         controller.setView(ncv);
         ((MainView) controller.mainView).setCurrentView(ncv);
         
      }
   }
   
   private class LoginButtonListener implements ActionListener {
      private Controller controller;
      private LoginView view;
      private Reservation reservation = null;
      
      LoginButtonListener(Controller controller, LoginView view) {
         this.controller = controller;
         this.view = view;
      }
      
      LoginButtonListener(Controller controller, LoginView view, Reservation reservation) {
         this.controller = controller;
         this.view = view;
         this.reservation = reservation;
      }
      
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         Customer dbCustomer = null;
         try {
            dbCustomer = model.getCustomer(view.getUsername());
            if(dbCustomer == null) {
               JOptionPane.showMessageDialog(null, String.format("%s does not exist", view.getUsername()), "New Customer Error Message", JOptionPane.ERROR_MESSAGE);
            } else {
               if(login(dbCustomer)) {
                  if(reservation == null) {
                     // This means the customer may be just reviewing current reservations
                     ReservationView rv = new ReservationView();
                     controller.setView(rv);
                     ((MainView) controller.mainView).setCurrentView(rv);
                  } else {
                     // Display reservation review prior to final submissions
                     ReservationView rv = new ReservationView();
                     rv.addReservation(reservation);
                     controller.setView(rv);
                     ((MainView) controller.mainView).setCurrentView(rv);
                  }
              }
            }
         } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         } catch (HeadlessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         } catch (InvalidKeySpecException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      }
      
      private boolean login(Customer dbCustomer) throws HeadlessException, NoSuchAlgorithmException, InvalidKeySpecException {
         boolean success = false;
         if(model.getDatabasePassword(dbCustomer.getPassword(), dbCustomer.getSalt()) != 
               model.getDatabasePassword(view.getPassword().toCharArray(), dbCustomer.getSalt())) {
            JOptionPane.showMessageDialog(null, "The password provided is not valid", "New Customer Error Message", JOptionPane.ERROR_MESSAGE);
         } else {
            JOptionPane.showMessageDialog(view, "Login Successful!");
            success = true;
         }
         return success;
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
         DetailsView dv = new DetailsView(findSelectedRoom((JButton)e.getSource()));
         controller.setView(dv);
         ((MainView) controller.mainView).setCurrentView(dv);
      }
      
      public Room findSelectedRoom(JButton button) {
         Room selectedRoom = null;
         for(RoomType r : RoomType.values()) {
            if(button.getName() == r.name()) {
               if(button.getName() == RoomType.PARADISE.name()) {
                  selectedRoom = new ParadiseRoom();
               } else if(button.getName() == RoomType.STUDIO.name()) {
                  selectedRoom = new StudioRoom();
               } else if(button.getName() == RoomType.SUITE.name()) {
                  selectedRoom = new SuiteRoom();
               }
            }
         }
         return selectedRoom;
      }
       
    }
    
    private class DetailsViewNextButtonListener implements ActionListener {
       private Controller controller;
       private DetailsView view;
       
       DetailsViewNextButtonListener(Controller controller, DetailsView view) {
          this.controller = controller;
          this.view = view;
       }

      @Override
      public void actionPerformed(ActionEvent e) {
         if(!view.validDate(view.getDateTextField())) {
            view.displayErrorMessage("The date must be in the form dd/MM/yyyy");
            return;
         }
         
         LoginView lv = new LoginView();
         controller.setView(lv);
         ((MainView) controller.mainView).setCurrentView(lv);
      }
       
    }
   
   private List<Room> createTestRooms() {
      ParadiseRoom pRoom = new ParadiseRoom();
      StudioRoom studioRoom = new StudioRoom();
      SuiteRoom suiteRoom = new SuiteRoom();
      List<Room> rooms = new ArrayList<>();
      rooms.add(pRoom);
      rooms.add(suiteRoom);
      rooms.add(studioRoom);
      return rooms;
   }
   
   private Reservation createReservation() {
      Customer customer = new Customer("test", "user", "password".toCharArray(), "test@test.com");
      Occupant occupant = new Occupant(customer, 2);
      Reservation r = null;
      r = new Reservation(occupant, new ParadiseRoom(), new Payment(), new Date() {}, 2, 179.99);
      return r;
   }
   
}
