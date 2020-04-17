import {Component} from '@angular/core';
import {AuthService} from "./auth.service";
import * as Stomp from "stompjs"
import * as SockJS from "sockjs-client"
import {Notification} from "./notification";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'WasteLess';
  wsUrl = 'http://localhost:8080/ws'
  notification: Notification;
  showNotification: boolean = false;

  client: any;

  constructor(private authService: AuthService) {
    this.connect();
  }

  logout() {
    this.authService.logout()
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  private connect() {
    let ws = new SockJS(this.wsUrl);
    this.client = Stomp.over(ws);
    let _this = this;

    this.client.connect({}, (frame) => {
      _this.client.subscribe('/notification/message', (notification) => {
        if (notification.body) {
          this.notification = JSON.parse(notification.body);
          this.notify();
        }
      })
    })
  }

  private notify() {
    this.showNotification = this.notification.message === 'show';
  }
}
