import java.io.{BufferedReader, File, FileReader}
import java.nio.charset.StandardCharsets

import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val file = new File(args(0));
    if(!file.exists()) {
      throw new Exception("The file does not exist");
    }
    if(!file.canRead()) {
      throw new Exception("Can't read")
    }
//    val source = Source.fromFile(file)
//    val lines = source.getLines().toList
//    
//    for(line <- lines) {
//      val category = getCategory(line)
//      println(line + ","+category)
//    }
    
    val out = new StringBuilder()
    
    val reader= new BufferedReader(new FileReader(file, StandardCharsets.ISO_8859_1))
    var line = reader.readLine()
    while(line != null ) {
      val categoryNode = getCategory(line)
      val categoryCsvs = categoryNode.getCsvs()
      out.append(line + "," +categoryNode.essentialness + "," + categoryCsvs +"\n")
      
      line = reader.readLine()
    }
    
    println(out)
  }
  
  case class Node(name: String, parent: Option[Node], essentialness: Essentialness) {
    def getCsvs(): String = { 
      parent match {
        case Some(p) => p.getCsvs()+","+name
        case None    => name  
      }
    }
  }
  
  case class Essentialness(str: String) {
    override def toString() = str
  }
  val Low = Essentialness("Low")
  val Medium = Essentialness("Medium")
  val High = Essentialness("High")
  val NA = Essentialness("N/A")
  
  
  val expenses = Node("Expenses", None, Medium)
    val clothing = Node("Clothing", Some(expenses), Medium)
    val lifeInsurance = Node("Life Insurance", Some(expenses), High)
    val politicalParties = Node("Political Parties", Some(expenses), Low)
    val charities = Node("Charities", Some(expenses), Low)
    val childcare = Node("Childcare", Some(expenses), High)
      val aprilChildcare = Node("April Childcare", Some(childcare), High)
      val robinChildcare = Node("Robin Childcare", Some(childcare), High)
    val mortgage = Node("Mortgage", Some(expenses), High)
    val cleaner = Node("Cleaner", Some(expenses), Low)
    val mattFun = Node("Matt Fun", Some(expenses), Low)
      val patreon = Node("Patreon", Some(mattFun), Low)
      val woodwork = Node("Woodwork", Some(mattFun), Low)
      val books = Node("Books", Some(mattFun), Low)
      val germanLessons = Node("German Lessons", Some(mattFun), Low)
    
    val internetServices = Node("Internet Services", Some(expenses), Medium)
    val food = Node("Food", Some(expenses), Low)
      val takeaway = Node("Takeaway", Some(food), Low)
      val eatingOut = Node("Eating out", Some(food), Low)
      val supermarket = Node("Supermarket", Some(food), High)
      val bakers = Node("Bakers", Some(food), Low)
      val cornerShops = Node("Corner shops", Some(food), Low)
      val vegBox = Node("Veg Box", Some(food), Low)
    val transport = Node("Transport", Some(expenses), Low)
      val publicTransport = Node("Public Transport", Some(transport), High)
      val car = Node("Car", Some(transport), Low)
    val kidsFun = Node("Kids Fun", Some(expenses), High)
      val softPlay = Node("Soft Play", Some(kidsFun), Medium)
    val healthItems = Node("Health Items", Some(expenses), High)
    val opticians = Node("Opticians", Some(expenses), High)
    val exercise = Node("Exercise", Some(expenses), Low)
      val personalTrainer = Node("Personal Trainer", Some(exercise), Low)
      val pilates = Node("Pilates", Some(exercise), Low)
      val beachBody = Node("BeachBody", Some(exercise), Low)
      val gymMembership = Node("Gym Membership", Some(exercise), Low)
    val homeImprovement = Node("Home Improvement", Some(expenses), Low)
    val drivingLessons = Node("Driving Lessons", Some(expenses), Medium)
    val utilities = Node("Utilities", Some(expenses), High)
      val mobilePhonesAndInternet = Node("Mobile Phone and Internet", Some(utilities), High)
      val energyBills = Node("Energy Bills", Some(utilities), High)
      val councilTax = Node("Council Tax", Some(utilities), High)
      val water = Node("Water", Some(utilities), High)
  val unknown = Node("Unknown", Some(expenses), Medium)
  
  val investments = Node("Investments", None, NA)
    val kidsIsas = Node("Kids Isas", Some(investments), NA)
    val vanguard = Node("Vanguard", Some(investments), NA)
  
  
  val transfers = Node("Transfers", None, NA)

  private def getCategory(orig: String): Node = {
    val str = orig.toUpperCase
    val cat = if(str.contains("APRIL TF CHILDCARE")) aprilChildcare
    else if(str.contains("ROBIN TF CHILDCARE")) robinChildcare
    else if(str.contains("TAX FREE CHILDCARE")) childcare
    else if(str.contains("SKIPTON")) mortgage
    else if(str.contains("ROBIN FARROW")) kidsIsas
    else if(str.contains("SMART JUNIOR ISA")) kidsIsas
    else if(str.contains("JUSTINA MARCIULION")) cleaner
    else if(str.contains("FRIENDS")) cleaner
    else if(str.contains("ANA PINTO")) cleaner
    else if(str.contains("PATREON")) patreon
    else if(str.contains("CURRY GARDEN")) takeaway
    else if(str.contains("BELLA NAPOLI")) takeaway
    else if(str.contains("JIMMY S PLAICE")) takeaway
    else if(str.contains("DELIVEROO")) takeaway
    else if(str.contains("RESTAURANT ASSOCIATES")) eatingOut
    else if(str.contains("PARK CAFE LONDON SE9")) eatingOut
    else if(str.contains("DANSON PARK KIOSK")) eatingOut
    else if(str.contains("COSTA COFFEE")) eatingOut
    else if(str.contains("LONDONCATERING")) eatingOut
    else if(str.contains("GREGGS")) eatingOut
    else if(str.contains("PRET A MANGER")) eatingOut
    else if(str.contains("PIZZAEXPRESSMOBILE")) eatingOut
    else if(str.contains("OCADO")) supermarket
    else if(str.contains("LIDL")) supermarket
    else if(str.contains("CO-OP")) cornerShops
    else if(str.contains("COSTCUTTER")) cornerShops
    else if(str.contains("COST CUTTER")) cornerShops
    else if(str.contains("LONDIS")) cornerShops
    else if(str.contains("NEWSAGENT")) cornerShops
    else if(str.contains("AYRES BAKERS")) bakers
    else if(str.contains("J AYRE ELTHAM")) bakers
    else if(str.contains("HOLLAND & BARRETT")) supermarket
    else if(str.contains("SAINSBURY")) supermarket
    else if(str.contains("JS ONLINE GROCERY")) supermarket
    else if(str.contains("ASDA")) supermarket
    else if(str.contains("MARKS&SPENCER")) food
    else if(str.contains("KENTVEGBOX")) vegBox
    else if(str.contains("WICKES")) woodwork
    else if(str.contains("B & Q")) woodwork
    else if(str.contains("DIY")) woodwork
    else if(str.contains("LEGAL & GEN")) lifeInsurance
    else if(str.contains("VITALITY LIFE")) lifeInsurance
    else if(str.contains("GREEN PARTY")) politicalParties
    else if(str.contains("LABOUR PARTY")) politicalParties
    else if(str.contains("MULTIPLE SCLEROSIS")) charities
    else if(str.contains("SHELTER")) charities
    else if(str.contains("APPLE.COM")) internetServices
    else if(str.contains("GOOGLE STORAGE")) internetServices
    else if(str.contains("AWS")) internetServices
    else if(str.contains("UNIQLO")) clothing
    else if(str.contains("HM.COM")) clothing
    else if(str.contains("EVERPRESS")) clothing
    else if(str.contains("VIVOBAREFOOT")) clothing
    else if(str.contains("CLOTHING")) clothing
    else if(str.contains("MOUNTAIN WAREHOUSE")) clothing
    else if(str.contains("FOOTWAY GROUP")) clothing
    else if(str.contains("TFL")) publicTransport
    else if(str.contains("DART-CHARGE")) car
    else if(str.contains("PARKING")) car
    else if(str.contains("ESURE")) car
    else if(str.contains("SERVICE STATION")) car
    else if(str.contains("MICHAEL ELEFTHERIO")) car
    else if(str.contains("LEBARA")) mobilePhonesAndInternet
    else if(str.contains("VODAFONE")) mobilePhonesAndInternet
    else if(str.contains("PILATES")) pilates
    else if(str.contains("LEDBULBS.CO.UK")) homeImprovement
//    else if(str.contains("AMAZON PRIME")) "Amazon prime membership"
    else if(str.contains("KOBO")) books
    else if(str.contains("WATERSTONES")) books
    else if(str.contains("GLL BETTER")) gymMembership
    else if(str.contains("COLIN HANMER")) personalTrainer
    else if(str.contains("BEACHBODY")) beachBody
    else if(str.contains("SJ O BRIEN")) drivingLessons
    else if(str.contains("EDF")) energyBills
    else if(str.contains("OUTFOX THE MARKET")) energyBills
    else if(str.contains("E.ON")) energyBills
    else if(str.contains("ROYAL GREENWICH")) councilTax
    else if(str.contains("THAMES WATER")) water
    else if(str.contains("SPECSAVERS")) opticians
    else if(str.contains("THEWORKS.CO.UK")) kidsFun
    else if(str.contains("Photobox")) kidsFun
    else if(str.contains("TK MAXX")) kidsFun
    else if(str.contains("PLAYCENTRAL GREENHITHE")) softPlay
    else if(str.contains("DINOTROPOLIS")) softPlay
    else if(str.contains("PHARMACYFIRST")) healthItems
    else if(str.contains("BOOTS")) healthItems
    else if(str.contains("VANGUARD")) vanguard
    else if(str.contains("MATT MONZO")) transfers
    else if(str.contains("EMILY GREEN")) transfers
    else if(str.contains("MATTHEW FARROW")) transfers
    else if(str.contains("CREATION.CO.UK")) transfers
    else if(str.contains("00691681")) germanLessons
    else unknown
    return cat
  }


}
