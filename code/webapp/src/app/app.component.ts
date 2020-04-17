import {Component} from '@angular/core';
import {AuthService} from "./auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'WasteLess';

  constructor(private authService: AuthService) {
  }

  logout() {
    this.authService.logout()
  }

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }
}
