window.addEventListener('load', function(e) {
  console.log('document loaded');
  
  init();
});



function init() {
	getAllActivities();
	
	document.createActivity.createActivity.addEventListener('click', createActivity);
}
function moreDetails(activity, div){
	  let editf = document.createElement('form');
	  let deletef = document.createElement('form');
	  let editButton = document.createElement('input');
	  let deleteButton = document.createElement('input');
	  let p = document.createElement('p');
	  let li1 = document.createElement('li');
	  let li2 = document.createElement('li');
	  let ul = document.createElement('ul');
	  
	  //button stuff
	  editButton.name="editButton";
	  editButton.type="button";
	  editButton.value="Edit";
	  editButton.class="btn btn-primary";
	  
	  deleteButton.name="deleteButton";
	  deleteButton.type="button";
	  deleteButton.value="delete";
	  deleteButton.class="btn btn-danger";
	  
	  editf.appendChild(editButton);
	  deletef.appendChild(deleteButton);
	  
	  editf.editButton.addEventListener('click', function(e){
		  editForm(activity);
	  });
	  
	  deletef.deleteButton.addEventListener('click', function(e){
		  deleteForm(activity);
	  });
	  
	  //list data
	  p.textContent = activity.description;
	  li1.textContent = activity.startDate;
	  li2.textContent = activity.endDate;
	  
	  //connecting
	  div.appendChild(p);
      div.appendChild(ul);
	  div.lastElementChild.appendChild(li1);
	  div.lastElementChild.appendChild(li2);
	  div.appendChild(editButton);
      div.appendChild(deleteButton);
      
}

function displayActitvities(activities) {
	  var dataDiv = document.getElementById('displayActivity');
	  dataDiv.textContent = '';
	  
	 for (let i = 0; i < activities.length; i++) {
		 
		  let div = document.createElement('div');
		  let h1 = document.createElement('h1');
		  div.class="activityDiv";
		  dataDiv.appendChild(div);
		  
		 
		  
		 
		  //activity DATA
		  h1.textContent = activities[i].name;
		  
		  
			  
		  //connecting everything
		  div.appendChild(h1);
		  
	      
	      div.addEventListener("click", function(e){
	    	  closeDetails();
	    	  moreDetails(activities[i], div);
	      });
		 
	 }
}

function closeDetails(dataDiv){
	var actDiv = document.getElementById('displayActivity').childNodes;
	console.log("in closeDetails "+actDiv[1]);
	
	for(let i = 0; i<actDiv.length; i++){
		let h1 = actDiv[i].firstElementChild;
		console.log('in for loop');
		while (actDiv[i].firstChild){
			console.log('in while loop'+actDiv[i].firstChild);
			
			actDiv[i].removeChild(actDiv[i].lastChild);
		}
		actDiv[i].appendChild(h1);
	}
}

function editForm(activity){
	document.createActivity.id.value=activity.id;
	document.createActivity.name.value=activity.name;
	document.createActivity.startDate.value=activity.startDate;
	document.createActivity.endDate.value=activity.endDate;
	document.createActivity.description.value=activity.description;
	document.createActivity.createActivity.removeEventListener('click', createActivity);
	
	document.createActivity.createActivity.name="editActivity";
	document.createActivity.editActivity.value="Edit Activity";
	document.createActivity.editActivity.addEventListener("click", editActivity );
	
	
}
		
function getAllActivities() {
	var xhr = new XMLHttpRequest();

	xhr.open('GET', 'api/activities/', true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			console.log(xhr.responseText);
			var data = JSON.parse(xhr.responseText);
			displayActitvities(data);
		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
			var activityData = document.getElementById('displayActivity');
			activityData.textContent = "No Activities were found";
		}
	};

	xhr.send(null);
}

function editActivity(e){
	e.preventDefault();
	
	console.log("in edit function");
		var xhr = new XMLHttpRequest();

		xhr.open('PUT', 'api/activities/'+ document.createActivity.id.value, true);
		
		xhr.setRequestHeader("Content-type", "application/json"); 
		
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status < 400) {
				console.log(xhr.responseText);
				var data = JSON.parse(xhr.responseText);
				document.createActivity.reset();
				document.createActivity.name.value='';
				document.createActivity.startDate.value='';
				document.createActivity.endDate.value='';
				document.createActivity.description.value='';
				document.createActivity.editActivity.value="Create Activity";
				document.createActivity.editActivity.removeEventListener('click', createActivity);
				document.createActivity.editActivity.name="createActivity";
				
				document.createActivity.createActivity.addEventListener('click', createActivity);
				getAllActivities();
				
			}

			if (xhr.readyState === 4 && xhr.status >= 400) {
				console.error(xhr.status + ': ' + xhr.responseText);
				var displayActivity = document.getElementById('displayActivity');
				displayActivity.textContent = "activities Not Found";
			}
		};
		
		var activity = {
				
				id: document.createActivity.id.value,
				name: document.createActivity.name.value,
				description: document.createActivity.description.value,
				startDate: document.createActivity.startDate.value,
				endDate: document.createActivity.endDate.value,
		}
		
		
		var activityObj = JSON.stringify(activity);

		xhr.send(activityObj);
}
function deleteForm(activity){
	
	console.log("in delete function");
	var xhr = new XMLHttpRequest();
	
	xhr.open('DELETE', 'api/activities/'+ activity.id, true);
	
	xhr.setRequestHeader("Content-type", "application/json"); 
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			console.log(xhr.responseText);
//			var data = JSON.parse(xhr.responseText);
			getAllActivities();
			
		}
		
		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
			var displayActivity = document.getElementById('displayActivity');
			displayActivity.textContent = "activities Not Found";
		}
	};
	
	
	
	xhr.send(null);
}

	 
	 
	  




//function getActorsFromFilm(filmId) {
//	  // TODO:
//	  // * Use XMLHttpRequest to perform a GET request to "api/films/"
//	  //   with the filmId appended.
//	  // * On success, if a response was received parse the film data
//	  //   and pass the film object to displayFilm().
//	  // * On failure, or if no response text was received, put "Film not found" 
//	  //   in the filmData div.
//		var xhr = new XMLHttpRequest();
//
//		xhr.open('GET', 'api/films/'+filmId+'/actors', true);
//		
//		xhr.onreadystatechange = function() {
//			if (xhr.readyState === 4 && xhr.status < 400) {
//				console.log(xhr.responseText);
//				var data = JSON.parse(xhr.responseText);
//				
//				displayActors(data);
//				
//			}
//
//			if (xhr.readyState === 4 && xhr.status >= 400) {
//				console.error(xhr.status + ': ' + xhr.responseText);
//				var filmData = document.getElementById('filmData');
//				filmData.textContent = "actors Not Found";
//			}
//		};
//
//		xhr.send(null);
//		
//	}



function createActivity(e) {
	e.preventDefault();
	console.log("in create function");
		var xhr = new XMLHttpRequest();

		xhr.open('POST', 'api/activities', true);
		
		xhr.setRequestHeader("Content-type", "application/json"); 
		
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status < 400) {
				console.log(xhr.responseText);
				var data = JSON.parse(xhr.responseText);
				getAllActivities();
				document.createActivity.reset();
				
			}

			if (xhr.readyState === 4 && xhr.status >= 400) {
				console.error(xhr.status + ': ' + xhr.responseText);
				var displayActivity = document.getElementById('displayActivity');
				displayActivity.textContent = "activities Not Found";
			}
		};
		
		var activity = {
				name: document.createActivity.name.value,
				description: document.createActivity.description.value,
				startDate: document.createActivity.startDate.value,
				endDate: document.createActivity.endDate.value,
		}
		
		
		var activityObj = JSON.stringify(activity);

		xhr.send(activityObj);
		
	}

