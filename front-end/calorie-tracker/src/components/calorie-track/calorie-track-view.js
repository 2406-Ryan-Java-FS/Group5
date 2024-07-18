import CalorieTrackTable from "./calorie-track-table";


export default function CalorieTrackView(){
    




    return (<div className="container">
        <h1>My calorie goal: </h1>
        <button type="button" className="btn btn-primary">+</button>
        <div className="week-button">
            <button type="button" className="btn btn-outline-primary btn-sm">Previous Week</button>
            <button type="button" className="btn btn-outline-primary btn-sm">Next Week</button>
        </div>
        <table className="table table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>7/15 M</th>
                    <th>7/16 T</th>
                    <th>7/17 W</th>
                    <th>7/18 Th</th>
                    <th>7/19 F</th>
                    <th>7/20 S</th>
                    <th>7/21 S</th>
                </tr>
            </thead>
            <tbody>
                <CalorieTrackTable/>
            </tbody>
        </table>
    
    </div>)
}