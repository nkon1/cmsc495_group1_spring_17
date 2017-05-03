package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import room.Room;
import room.RoomType;

public class SelectRoomView extends JPanel implements View {
   private static final long serialVersionUID = -3973966279112037065L;
   private List<Room> rooms;
   private List<JButton> selectionButtons = new ArrayList<>();
   private Room selectedRoom;
   
   public SelectRoomView(List<Room> rooms) {
      this.rooms = rooms;
      this.add(createSelectRoomView());
   }
   
   public JPanel createSelectRoomView() {
      JPanel panel = new JPanel(new GridBagLayout());
      for(RoomType roomType : RoomType.values()) {
         JPanel tPanel = createRoomType(roomType);
         panel.add(tPanel);
      }
      return panel;
   }
   
   private JPanel createRoomType(RoomType roomType) {
      Room sampleRoom = null;
      for(Room room : rooms) {
         if(room.getRoomType() == roomType) {
            sampleRoom = room;
            break;
         }
      }
      
      JPanel panel = new JPanel(new GridBagLayout());
      panel.setBorder(BorderFactory.createRaisedBevelBorder());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.insets = new java.awt.Insets(5,5,5,5);
      c.fill = GridBagConstraints.BOTH;
      
      JPanel room = new JPanel(new GridLayout(3, 0));
      JLabel roomLabel = new JLabel();
      roomLabel.setFont(new Font(Font.MONOSPACED, Font.ROMAN_BASELINE, 20));
      roomLabel.setText(roomType.name());
      JLabel costLabel = new JLabel(String.format("Price Per Night %.2f", sampleRoom.getCost()));
      JLabel availRoomsLabel = new JLabel(String.format("Available Rooms %d", countRooms(roomType)));
      room.add(roomLabel);
      room.add(costLabel);
      room.add(availRoomsLabel);
      panel.add(room, c);
      
      c.gridx = 0;
      c.gridy = 1;
      JPanel features = new JPanel(new GridLayout(3, 0));
      features.setBorder(BorderFactory.createTitledBorder("Features"));
      JLabel jacuzziLabel = new JLabel("Has Jacuzzi " + sampleRoom.hasJacuzzi());
      JLabel fireplaceLabel = new JLabel("Has Fireplace " + sampleRoom.hasFireplace());
      JLabel microwaveLabel = new JLabel("Has Microwave " + sampleRoom.hasMicrowave());
      features.add(jacuzziLabel);
      features.add(fireplaceLabel);
      features.add(microwaveLabel);
      panel.add(features, c);
      
      c.gridx = 0;
      c.gridy = 2;
      JPanel buttonPanel = new JPanel();
      JButton selectionButton = new JButton("Select");
      selectionButton.setName(roomType.name());
      selectionButtons.add(selectionButton);
      buttonPanel.add(selectionButton);
      
      panel.add(buttonPanel, c);
      
      return panel;
   }
   
   private int countRooms(RoomType roomType) {
      int count = 0;
      for(Room room : rooms) {
         if(room.getRoomType() == roomType) 
            count++;
      }
      return count;
   }
   
   public void setSelectedRoom(Room room) {
      this.selectedRoom = room;
   }
   
   public void addSelectRoomActionListener(JButton button, ActionListener a) {
        button.addActionListener(a);
   }
   
   public Room getSelectedRoom() {
      if(selectedRoom == null)
         return null;
      
      return selectedRoom;
   }
   
   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.SELECT_ROOM;
   }
   
   public List<JButton> getSelectionButtons() {
      return selectionButtons;
   }

}
