

window.addEventListener('load', function(e) {
  console.log('document loaded');
  console.log('document hello');
  getAllCategories()
  
});

function getAllCategories() {
	var xhr = new XMLHttpRequest();
	
	xhr.open('GET', 'api/categories/', true);
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			console.log(xhr.responseText);
			var data = JSON.parse(xhr.responseText);
			console.log("in get all cat: "+data)
			init(data);
			
		}
		
		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
			var activityData = document.getElementById('displayActivity');
			activityData.textContent = "No Activities were found";
		}
	};
	
	xhr.send(null);
}


function init(categories) {
	
	
	var select =document.createElement("select");
	select.name="select";
	select.setAttribute("multiple", null);
	select.setAttribute("size", 5);
	
	for(let i = 0; i< categories.length; i++){
		let option = document.createElement("option");
		option.textContent = categories[i].name;
		option.value = categories[i].id;
		select.appendChild(option);
	}
	document.createActivity.appendChild(select);
	let input = document.createElement('input');
	input.name = 'createActivity';
	input.type = 'button';
	input.classList.add('btn');
	input.classList.add('btn-primary');
	input.value = 'Create Activity';
	
	document.createActivity.appendChild(input);
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
	  let li3 = document.createElement('li');
	  let ul = document.createElement('ul');
	  let ul2 = document.createElement('ul');
	  
	  //button stuff
	  editButton.name="editButton";
	  editButton.type="button";
	  editButton.value="Edit";
	  editButton.classList.add('btn');
	  editButton.classList.add('btn-primary');
	  
	  deleteButton.name="deleteButton";
	  deleteButton.type="button";
	  deleteButton.value="delete";
	  deleteButton.classList.add('btn');
	  deleteButton.classList.add('btn-danger');
	  
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
	  li3.textContent = "categories: ";
	  li3.appendChild(ul2);
	  for(let i = 0; i< activity.categories.length; i++){
		  let li = document.createElement('li');
		  li.textContent = activity.categories[i].name;
		  ul2.appendChild(li);
		  
	  }
	  
	  //connecting
	  div.appendChild(p);
      div.appendChild(ul);
	  div.lastElementChild.appendChild(li1);
	  div.lastElementChild.appendChild(li2);
	  div.lastElementChild.appendChild(li3);
	  div.appendChild(editButton);
      div.appendChild(deleteButton);
      
      console.log("Categories: "+activity.categories);
}

function displayActitvities(activities) {
	  var dataDiv = document.getElementById('displayActivity');
	  dataDiv.textContent = '';
	  console.log("in display Activities");
	  
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
	    	  console.log("going to more details");
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
	let children = document.createActivity.select.children;
	for(let i =0; i<children.length; i++){
		
		for(let j = 0; j < activity.categories.length; j++){
			
			if(activity.categories[j].name === children[i].textContent){
				children[i].setAttribute('selected', null)
			}
		}
		
	}
	
	
	
	
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
				for(let i = 0; i<document.createActivity.select.children.length; i++){
					document.createActivity.select.children[i].removeAttribute('selected');
				}
				
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
		console.log("value of: " + document.createActivity.select.value);
		var activity = {
				
				id: document.createActivity.id.value,
				name: document.createActivity.name.value,
				description: document.createActivity.description.value,
				startDate: document.createActivity.startDate.value,
				endDate: document.createActivity.endDate.value,
				categories: [],
		}
		
		for(let i = 0 ; i< document.createActivity.select.options.length; i++){
			if(document.createActivity.select.options[i].selected){
				let cid =document.createActivity.select.options[i].value;
				activity.categories.push({"id": cid});
			}
		}
		
		
		var activityObj = JSON.stringify(activity);
		console.log('activity obj: '+activityObj)

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
				categories: [],
		}
		
		for(let i = 0 ; i< document.createActivity.select.options.length; i++){
			if(document.createActivity.select.options[i].selected){
				let cid =document.createActivity.select.options[i].value;
				activity.categories.push({"id": cid});
			}
		}
		
		var activityObj = JSON.stringify(activity);

		xhr.send(activityObj);
		
	}

