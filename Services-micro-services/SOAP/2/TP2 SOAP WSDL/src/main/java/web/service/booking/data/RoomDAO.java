package web.service.booking.data;

import web.service.booking.models.Room;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoomDAO implements DBDAO<Room> {
   private Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;
    private String DB_NAME;

    public RoomDAO() {
    }



    public RoomDAO(Connection connection) {
        this.connection = connection;
    }


    private Connection getConnection() throws SQLException {
    return this.connection;
    }





    @Override
    public boolean createTable()  {

        String sqlCreateImgQueryString = "CREATE TABLE IF NOT EXISTS  IMAGES   " +
                "( Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "imgName TEXT(4000)  NOT NULL,  " +
                "IMG BLOB(4194304)  NOT NULL);";

        String sqlQueryString = "CREATE TABLE IF NOT EXISTS ROOMS (\n" +
                "    Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                "    roomNumber INT NOT NULL UNIQUE,\n" +
                "    numberBed INT NOT NULL,\n" +
                "    basePrice DECIMAL(7,2) NOT NULL,\n" +
                "    imgId INT, \n" +
                "FOREIGN KEY (imgId) REFERENCES IMAGES(Id) ON DELETE SET NULL "+
                ");";
        try  {

//            System.out.println(this.jdbcConnectionFactory.getConnectionDB());
            this.ptmt = this.connection.prepareStatement(sqlCreateImgQueryString);
            ptmt.execute();
            this.ptmt = this.connection.prepareStatement(sqlQueryString);
            ptmt.execute();
            return true;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
            return false;
        }


    }

    private Room createRoom(ResultSet resultSet) throws SQLException {
        return new Room(resultSet.getInt("roomNumber"),
                resultSet.getInt("basePrice"),
                resultSet.getDouble("basePrice"));
    }

    private void mutedClose(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(Room room)  {
         try {
             String sqlQueryString =  "INSERT INTO ROOMS(roomNumber, numberBed, basePrice, imgId) VALUES(?,?,?, ?)";
//             connection = this.connection;
            ptmt = connection.prepareStatement(sqlQueryString);
             ptmt.setString(1, Integer.toString(room.getRoomNumber()));
             ptmt.setString(2, Integer.toString(room.getNumberBed()));
             ptmt.setString(3, Double.toString(room.getBasePrice()));
             ptmt.setString(4, Double.toString(room.getImgId()));
            ptmt.executeUpdate();
            return true;
        } catch (SQLException e) {
//            throw new RuntimeException(e);
             return false;
        }


    }



    @Override
    public boolean update(Room room) {
        boolean success = false;
        String sql = "UPDATE ROOMS SET roomNumber=?, numberBed=?, basePrice=?, available=?, imgURL=? WHERE Id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, room.getRoomNumber());
            stmt.setInt(2, room.getNumberBed());
            stmt.setDouble(3, room.getBasePrice());
            stmt.setBoolean(4, room.isAvailable());
            stmt.setString(5, room.getImgName());
//            stmt.setInt(6, room.getId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }



    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM ROOMS";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Room room = new Room();
//                room.setId(rs.getInt("Id"));
                room.setRoomNumber(rs.getInt("roomNumber"));
                room.setNumberBed(rs.getInt("numberBed"));
                room.setBasePrice(rs.getDouble("basePrice"));
                room.setImgId(rs.getInt("imgId"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> get(String sqlQuery) {
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room room = new Room();
//                room.setId(rs.getInt("Id"));
                room.setRoomNumber(rs.getInt("roomNumber"));
                room.setNumberBed(rs.getInt("numberBed"));
                room.setBasePrice(rs.getDouble("basePrice"));
                room.setAvailable(rs.getBoolean("available"));
                room.setImgName(rs.getString("imgURL"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> get(String sqlQuery, Object... args) {
        List<Room> rooms = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            // set the parameters in the PreparedStatement
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            // execute the query and get the ResultSet
            ResultSet resultSet = statement.executeQuery();

            // iterate over the ResultSet and create Room objects
            while (resultSet.next()) {
                Room room = new Room();
//                room.setId(resultSet.getInt("Id"));
                room.setRoomNumber(resultSet.getInt("roomNumber"));
                room.setNumberBed(resultSet.getInt("numberBed"));
                room.setBasePrice(resultSet.getDouble("basePrice"));
                room.setImgName(resultSet.getString("imgURL"));
                room.setAvailable(resultSet.getBoolean("available"));
                rooms.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }


    public int insertImg(int roomType, int imgNumber, Room room) {
        ClassLoader fileLoader = getClass().getClassLoader();
        String imgPath = "img/room/"+Integer.toString(roomType)+"/"+Integer.toString(imgNumber)+".jpg";
        boolean rowInsertion =false;
        int key=-1;

        String sqlStmt = "INSERT INTO IMAGES (imgName, IMG) VALUES (?, ?)";
        try {
          FileInputStream  fileInputStream = new FileInputStream(new File(fileLoader.getResource(imgPath).toURI()));
            System.out.println("Image path: "+imgPath);
            System.out.println("Absolute Path: "+new File(fileLoader.getResource(imgPath).toURI()).getAbsolutePath());

                PreparedStatement insertion = connection.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS);
                insertion.setString(1, room.getImgName());
                insertion.setBinaryStream(2, fileInputStream, fileInputStream.available());
                rowInsertion = insertion.execute();

                ResultSet genRS = insertion.getGeneratedKeys();
                while (genRS.next()) {
                    key = Integer.parseInt(Long.toString(genRS.getLong(1)));
                    System.err.println("New img key from inside RoomDAO: " + key);
                }
        } catch (FileNotFoundException e) {
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return key;
    }


  public URL getImageURLFromRetrieveAndSave(String roomImageName) {
      ClassLoader classLoader = getClass().getClassLoader();
      String imName = roomImageName.trim();
      String sqlStmt = "SELECT IMG FROM IMAGES WHERE imgName = ?";
      String pathTest = "requestedIMG/";
      URL retURL = null;


      try {
          PreparedStatement retrieve = connection.prepareStatement(sqlStmt, Statement.RETURN_GENERATED_KEYS );
          retrieve.setString(1, imName);
          File file = new File(classLoader.getResource(pathTest).toURI());

          String dirPath = file.getAbsolutePath();
          System.out.println("Dir path: "+ dirPath);
          String pathedIMG =dirPath+"/"+imName+".jpg";
          System.err.println("Pathed file: "+pathedIMG);

          File newFile = new File(pathedIMG);
          ResultSet resultSet = retrieve.executeQuery();

          if (resultSet.next()) {
              Blob blob = resultSet.getBlob(1);
              byte barr[] = blob.getBytes(1,(int) blob.length());
              FileOutputStream fileOutputStream = new FileOutputStream(newFile);
              fileOutputStream.write(barr);
              fileOutputStream.close();
              URI fileUri = newFile.toURI();

              URL fileUrl = fileUri.toURL();
                retURL = fileUrl;
              System.out.println("New file's url: "+retURL);
          }
      } catch (SQLException e) {
//          throw new RuntimeException(e);
      } catch (MalformedURLException e) {
//          throw new RuntimeException(e);
      } catch (FileNotFoundException e) {
//          throw new RuntimeException(e);
      } catch (URISyntaxException e) {
//          throw new RuntimeException(e);
      } catch (IOException e) {
//          throw new RuntimeException(e);
      }
      return retURL;
    }




    @Override
    public boolean dropTableOrDB(String tableOrDB) {
        boolean isDropped = false;
        try {
//            Connection connection = this.connection;
            Statement statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS ROOMS" ;
            statement.executeUpdate(query);
            isDropped = true;
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error while dropping table/database: " + e.getMessage());
        }
        return isDropped;
    }
}




//    public  void saveImageToFile(String id, String filename) throws SQLException, IOException {
//        BufferedImage image = getImageData(id);
//        File outputFile = new File(filename);
//        File parentDirectory = outputFile.getParentFile();
//        if (!parentDirectory.exists()) {
//            parentDirectory.mkdirs();
//        }
//        ImageIO.write(image, "jpg", outputFile);
//    }


//    @Override
//    public List<Room> getAll()  {
//        return null;
//    }
//
//    @Override
//    public boolean update(Room room)  {
//        return false;
//    }
//
//    @Override
//    public List<Room> get(String sqlQuery) {
//        return null;
//    }
//


//        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
//                                                        image.getHeight(null),
//                                                        BufferedImage.TYPE_INT_ARGB);
//
//        Graphics2D g2 = bufferedImage.createGraphics();
//        g2.drawImage(image, 0, 0, null);
//        g2.dispose();

//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        byte[] imageData = byteArrayOutputStream.toByteArray();
//        InputStream imgInputStream =  new ByteArrayInputStream(imageData);




//    public  BufferedImage getImageData(String roomImName)  {
////        PreparedStatement statement = null;
//        BufferedImage img=null;
//
//        String sqlQ="SELECT IMG FROM IMAGES WHERE imgName = ?";
//        String rmIMNM = "";
//        try (PreparedStatement statement = connection.prepareStatement(sqlQ);
////            final   String rmIMNM= new String()roomImName;
//            ) {
//            statement.setString(1, roomImName.trim());
////        System.out.println("received img name: "+roomImName);
//            ResultSet result = statement.executeQuery();
//            while (result.next()) {
//                Blob blob = result.getBlob("IMG");
//                System.out.println("retrieved blob: "+blob.length());
//                InputStream inputStream = new ByteArrayInputStream(blob.getBytes(0L, (int) blob.length()));
//                System.out.println("inputStream: "+ inputStream.read());
//                BufferedImage image = ImageIO.read(inputStream);
//                img = image;
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                ImageIO.write(image, "jpg", baos);
//                inputStream.close();
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        return img;
//    }

//    public static boolean saveImageToFile(String roomImgName, BufferedImage image, String directoryPath) throws SQLException, IOException {
//        File directory = new File(directoryPath);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//        File outputFile = new File(directory, roomImgName + ".jpg");
//       return ImageIO.write(image, "jpg", outputFile);
//    }


