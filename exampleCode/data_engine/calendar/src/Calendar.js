import React from "react";

class Calendar extends React.Component{
  constructor(props){
    super(props);


    this.today = 0;
    this.today = new Date();
    this.dd = this.today.getDate();
    this.mm = this.today.getMonth() + 1;
    this.yyyy = this.today.getFullYear();
    if (this.dd<10){
      this.dd = '0' + this.dd;
    }
    if (this.mm<10){
      this.mm='0' + this.mm;
    }

    this.currDateString = this.mm + '/' + this.dd + '/' + this.yyyy;

    this.state = {
      currDate: this.currDateString
    }
  }
  toString(){
    return super.toString()+ 'Date:' + this.currDateString;
  }

  reloadDate = () => {
    this.setState({
      currDate: this.currDateString
    });
  }


  calDate = (date) => {
    //this.today = date;
    this.dd = date;
    this.mm = this.today.getMonth() + 1;
    this.yyyy = this.today.getFullYear();
    if (this.dd<10){
      this.dd = '0' + this.dd;
    }
    if (this.mm<10){
      this.mm='0' + this.mm;
    }

    return (this.mm + '/' + this.dd + '/' + this.yyyy);

  }

  clearDate = () => {
    var poop = this.calDate(12);
    this.setState({
      currDate: poop
    });
  }


  render() {
    const styles = {
      calendar: {
        color: "red",
        fontSize: "6em",
        fontFamily: "Courier New"
      }
    }
    return (
      <div>
        <h3>{this.props.name}</h3>
        <h4>{this.props.children}</h4>
        <p>Fucking bitch</p>
        <h4 style={styles.calendar}>{this.state.currDate}</h4>
        <button onClick={this.reloadDate}>Reload Date</button>
        <button onClick={this.clearDate}>Clear Date</button>
      </div>
    );
  }

}

export default Calendar;
