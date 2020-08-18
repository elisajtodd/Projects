# PassingBy-EP-Strong-Hackathon-
Scope <br>
	This project involves the creation of a community driven application that will encourage the growth of local businesses, participation in community events, and promote social well-being. The system will keep track of events happening or that will happen in the city of El Paso, it will categorize the events, and provide rewards based on activities completed. Users will be required to make an account in which they will be able to see events either close to their current location or on a city-wide scale in addition to being able to filter the events based on interests. There will be two types of users (player and admin), and a temporary upgrade for the player user. This temporary upgrade to the user is to give them the option to host an event. This upgrade will be given only with permission from the admins and a formal request (name, description, times, location and proof of verification) will be needed. The user functions as a host include managing volunteers (if any), viewing statistics of players participation (usernames of participants are hidden from the host), and receiving point achievements. This upgrade will be active only during the event. Moreover, a regular user will have the ability to explore the city of El Paso and receive points by doing so. These points are used to rank players and give them material benefits such as small discounts at participating businesses around town, or buying aesthetics for use within the app. Events will have tags in order to help users filter them and find events that are relevant to them.Some examples of potential events would be a concert at a local venue, attending El Paso Strong Hackathon, or an open mic night at a coffee shop, and a new exhibit opening at the Museum of Art.  Each activity counts a different value to the score. The app will verify that a user has been to an event/landmark by using their device’s location to verify that they are close enough to the event to receive points.If a community/location milestone is reached, the owner might choose to make a special event to reward the community while at the same time promoting their business. The prize milestones would be reset after a given period of time (once a month), in order for the discounts and other similar rewards to not be exploited, the points for locations or events would be given only once a day(or other reasonable amount of time depending on the event/location).The El Pasoans will work together to reach their community milestones and hopefully surpass them. Ultimately, this app aims to increase the sense of community amongst El Pasoans.
<br>


Restrictions: <br>
1- For creating an account the next things are required: username (not already taken), password, and email. <br>
2-  Landmarks will be used for points only once per day <br>
3- The upgraded version of player (hosting player) will only be activated during the time of the event, and with permission from the admin <br>
4- Permission for the app to use the location will be required <br>
5- Points will only be awarded from 5 am to 10 pm (for security) with the exception of already stated hours in the event. <br>
6- Hosting Events will only be permitted by filling the required form with the required information (Name of Event, Location in the city, Description of event, If volunteers will be accepted, Purpose of the event (either cultural or to help local business), proposition of points awarded (admins will decide at the end how many points will be given), proof of authenticity of event (an admin might be attending the event to make sure it happens) and rewards given by the event or business). <br>
7- The first steps of the app will be restricted to El Paso, and later on other cities will be added to promote a small competition between cities. <br>
.

Assumptions: <br>
Assume that local businesses would actually want to participate and give discounts to users <br>



Classes: <br>
	Landmarks: <br>
		Will include: Name, Description, Location,  Active Time (6 am - 10 pm in case of a landmark and working hours in case of a business), Point value <br>
	Events: <br>
		Will include: Name, description, Location, Host, Point Value, Hours, if accepting volunteers requirements (if applicable) and a description of what will be doing. <br>
	User: <br>
		Will include: Username, Password, Location, Community Points, Solo Points, Awards on inventory, Type (Hosting, normal or admin), Rank in community, Achievements. <br>
