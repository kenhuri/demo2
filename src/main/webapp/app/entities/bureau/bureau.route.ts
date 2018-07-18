import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Bureau } from 'app/shared/model/bureau.model';
import { BureauService } from './bureau.service';
import { BureauComponent } from './bureau.component';
import { BureauDetailComponent } from './bureau-detail.component';
import { BureauUpdateComponent } from './bureau-update.component';
import { BureauDeletePopupComponent } from './bureau-delete-dialog.component';
import { IBureau } from 'app/shared/model/bureau.model';

@Injectable({ providedIn: 'root' })
export class BureauResolve implements Resolve<IBureau> {
    constructor(private service: BureauService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((bureau: HttpResponse<Bureau>) => bureau.body));
        }
        return of(new Bureau());
    }
}

export const bureauRoute: Routes = [
    {
        path: 'bureau',
        component: BureauComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Bureaus'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bureau/:id/view',
        component: BureauDetailComponent,
        resolve: {
            bureau: BureauResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bureaus'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bureau/new',
        component: BureauUpdateComponent,
        resolve: {
            bureau: BureauResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bureaus'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bureau/:id/edit',
        component: BureauUpdateComponent,
        resolve: {
            bureau: BureauResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bureaus'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bureauPopupRoute: Routes = [
    {
        path: 'bureau/:id/delete',
        component: BureauDeletePopupComponent,
        resolve: {
            bureau: BureauResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bureaus'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
