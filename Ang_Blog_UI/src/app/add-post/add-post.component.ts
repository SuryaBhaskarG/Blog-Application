import { PostPayload } from './post-payload';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AddPostService } from '../add-post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {
  addPostForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private addPostService: AddPostService,
    private router: Router    
  ) {
    this.addPostForm = this.formBuilder.group({
      title: [''], // Initialize title form control
      body: ['']   // Initialize body form control
    });
  }

  ngOnInit() {}

  addPost() {
    const bodyControlValue = this.addPostForm.get('body')?.value;
    const titleControlValue = this.addPostForm.get('title')?.value;
  
    // Ensure both body and title controls are defined
    if (bodyControlValue !== undefined && titleControlValue !== undefined) {
      // Create a new instance of PostPayload with title and body
      const postPayload: PostPayload = {
        id: '', // You may need to generate a unique ID here
        content: bodyControlValue,
        title: titleControlValue,
        username: '' // You may need to get the current user's username here
      };
  
      // Call the addPost method of the service to save the post
      this.addPostService.addPost(postPayload).subscribe(
        () => {
          // Redirect to home page after successful post submission
          this.router.navigateByUrl('/');
        },
        (error) => {
          console.error('Failed to add post:', error);
        }
      );
    }
  }
}
