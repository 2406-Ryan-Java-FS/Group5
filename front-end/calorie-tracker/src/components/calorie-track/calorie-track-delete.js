import { useEffect, useState } from "react";
import {  useParams, useNavigate, Link } from "react-router-dom"

export default function CalorieTrackDelete(){
    const [calorieTrack, setCalorieTrack] = useState(null);
    const[success, setSuccess] = useState(false);
    const {cId} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
      if(cId){
        fetch(`http://localhost:8080/api/calorietrack/${cId}`)
          .then(res => {
            if(res.ok){
              const body = res.json();
              console.log("fetch calorietrack success: " + body);
              return body;
            }else{
              return Promise.reject(
                new Error(`Unexpected status code: ${res.status}`)
              );
            }
          })
          .then(setCalorieTrack)
          .catch(error => {
            console.error(error);
            navigate("/calorietrack");
          })
      }
    }, [])

    function handleDelete(){
      const config = {
        method: "DELETE",
      };

      fetch('http://localhost:8080/api/calorietrack/' + cId, config)
      .then(res => {
        if(res.ok){
          setSuccess(true);
          setTimeout(() => {
            navigate('/calorietrack');
          }, 2000);
        }else{
          return Promise.reject(
            new Error(`Unexpected status code ${res.status}`)
          );
        }
      })
      .catch(error => {
        navigate('/calorietrack')
      })
    }
    
    if (!calorieTrack) {
      return (
        <div
          style={{ minHeight: '80vh' }}
          className='d-flex justify-content-center align-items-center'>
          <div className='spinner-border ' role='status'>
            <span className='visually-hidden'>Loading...</span>
          </div>
        </div>
      );
    }
    return (
      <div className="container col-lg-6 col-sm-8  pb-4 rounded-2" id="reviewDeleteBox">
          {success && (
          <div className='alert alert-success' role='alert'>
            <p>
              Successfully deleted the log! Navigating back to
              your calorie track page ...{' '}
            </p>
          </div>
        )}
        <h2 className="pt-2">Delete Calorie Log</h2>
        <div className="mb-2">
                  <div className="confirmMsg">
                      <b>Are you sure you want to delete the following Log?</b>
                  </div>
          <ul>
            <li>
              <span className="detailTitles">FoodName:</span> {calorieTrack.food.foodName}
            </li>
            <li><span className="detailTitles">calorie:</span> {calorieTrack.food.calorie}</li>
            <li><span className="detailTitles">servings: </span> {calorieTrack.serving} </li>
            <li><span className="detailTitles">servings: </span> {calorieTrack.logDate} </li>
          </ul>
            </div>
              <div className="mb-2 d-flex justify-content-end">
                  <button onClick={handleDelete} className='btn btn-success' id="deleteConfirmBtn">
                 Delete
                  </button>
                  <Link className='btn btn-success ms-2' to={`/calorietrack`} id="deleteCancelBtn"> Cancel</Link>
              </div>
                  
          
        
        
      </div>
    );
  
}