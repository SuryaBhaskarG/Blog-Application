import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {PostPayload} from './add-post/post-payload';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddPostService {

  constructor(private httpClient: HttpClient) {
  }

  addPost(postPayload: PostPayload){
    return this.httpClient.post('http://localhost:8080/api/posts/addPost', postPayload);
  }

  getAllPosts(): Observable<Array<PostPayload>>{
    return this.httpClient.get<Array<PostPayload>>("http://localhost:8080/api/posts/all");
  }

  /*getPost(permaLink: Number):Observable<PostPayload>{
    return this.httpClient.get<PostPayload>('http://localhost:8080/api/posts/get/' + permaLink);
  } */

  getPost(permaLink: Number): Observable<PostPayload> {
    let yourJwtToken = localStorage.getItem('5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437'); // replace 'yourJwtTokenKey' with the key you used to store the JWT token in local storage
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + yourJwtToken
    });
    return this.httpClient.get<PostPayload>('http://localhost:8080/api/posts/get/' + permaLink, { headers: headers });
  }


}